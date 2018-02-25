package com.horizon.common.util;


import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;

public class JsonIoConvertor implements IoConvertor {

	private static HashMap<Class<?>, Class<?>> DeserializeClassesMap = new HashMap<Class<?>, Class<?>>();
	static {
		DeserializeClassesMap.put(boolean.class, Boolean.class);
		DeserializeClassesMap.put(int.class, Integer.class);
		DeserializeClassesMap.put(long.class, Long.class);
		DeserializeClassesMap.put(float.class, Float.class);
		DeserializeClassesMap.put(double.class, Double.class);
	}

	// JSON
	private static ObjectMapper MAPPER = new ObjectMapper();
	static {
		AnnotationIntrospector jackson = new JacksonAnnotationIntrospector();
		AnnotationIntrospector jaxb = new JaxbAnnotationIntrospector(
				TypeFactory.defaultInstance());
		AnnotationIntrospector pair = AnnotationIntrospector
				.pair(jaxb, jackson);
		MAPPER.setAnnotationIntrospector(pair);
		// �趨��������
		MAPPER.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
				false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,
				false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS,
				false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY,
				false);
		MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
	}

	// �ı�����
	private String charset = "UTF-8";

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@Override
	public Object read(InputStream in, Class<?> clazz) throws Throwable {
		return MAPPER.reader(clazz).readValue(in);
	}

	@Override
	public Object read(String content, Class<?> clazz) throws Throwable {
		return MAPPER.reader(clazz).readValue(content);
	}

	@Override
	public Object readArray(String content) throws Throwable {
		return ((ArrayNode) read(content, ArrayNode.class)).iterator();
	}
	
	public List<?> readList(String jsonArray, Class<?> clazz) throws Throwable{
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(ArrayList.class, clazz); 
		return MAPPER.readValue(jsonArray, javaType); 
	}


	@Override
	public Object readObject(Object object, Class<?> clazz) throws Throwable {
		JsonNode node = (JsonNode) object;
		if (node.isNull()) {
			return null;
		} else if (node.isObject()) {
			return MAPPER.reader(clazz).readValue(node);
		} else {
			return (DeserializeClassesMap.containsKey(clazz) ? DeserializeClassesMap
					.get(clazz) : clazz).getConstructor(String.class)
					.newInstance(node.asText());
		}
	}

	@Override
	public void write(OutputStream out, Object object) throws Throwable {
		if (object != null) {
			if (object.getClass() == String.class)
				out.write(((String) object).getBytes(charset));
			else
				MAPPER.writeValue(out, object);
		}
	}

	@Override
	public String write(Object object) throws Throwable {
		return object == null ? null
				: (object.getClass() == String.class ? (String) object : MAPPER
						.writeValueAsString(object));
	}

}

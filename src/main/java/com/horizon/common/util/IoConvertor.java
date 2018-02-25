package com.horizon.common.util;


import java.io.InputStream;
import java.io.OutputStream;

public interface IoConvertor {

	public Object read(InputStream in, Class<?> clazz) throws Throwable;

	public Object read(String content, Class<?> clazz) throws Throwable;

	public Object readArray(String content) throws Throwable;

	public Object readObject(Object object, Class<?> clazz) throws Throwable;

	public void write(OutputStream out, Object object) throws Throwable;

	public String write(Object object) throws Throwable;
}

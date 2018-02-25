package com.horizon.common.base;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import com.horizon.common.util.ThreeDESUtil;

public class Config extends PropertyPlaceholderConfigurer {
	
	/**
	 * 日志对象
	 */
	public Log log = LogFactory.getLog(getClass());

	public static Properties props;

	@Override
	protected void loadProperties(Properties property) throws IOException {
		super.loadProperties(property);
		props = property;
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}// 需要加密的属性名称

	private String[] encryptPropNames = { "jdbc.user", "jdbc.password" };

	/**
	 * 转换加密的属性
	 * 
	 * @param propertyName
	 *            属性名 propertyValue 属性值
	 * @return 解密后的值
	 */
	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptProp(propertyName)) {
			byte[] decryptValue = null;
			decryptValue = ThreeDESUtil.decryptMode(Base64
					.decodeBase64(propertyValue.getBytes()));
			propertyValue = new String(decryptValue);
			return propertyValue;
		} else {
			return propertyValue;
		}
	}

	/**
	 * 判断是否是加密的属性
	 * 
	 * @param propertyName
	 *            属性名称
	 * @return boolean
	 */
	private boolean isEncryptProp(String propertyName) {
		for (String encryptpropertyName : encryptPropNames) {
			if (encryptpropertyName.equals(propertyName))
				return true;
		}
		return false;
	}

}

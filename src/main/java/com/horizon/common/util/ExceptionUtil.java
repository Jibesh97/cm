package com.horizon.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.apache.log4j.Logger;


public class ExceptionUtil {

	private ExceptionUtil(){};
	
	public static void printStackTrace(Exception ex,Logger log){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream p = new PrintStream(os);
		ex.printStackTrace(p);
		log.error(os.toString());
	}
}

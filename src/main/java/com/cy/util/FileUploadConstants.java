package com.cy.util;
import java.io.InputStream;
import java.util.Properties;

public class FileUploadConstants {
	public static String EXCELPATH_USER = "";
	private static Properties prop=null;
	
	static{
		@SuppressWarnings("unused")
		String path="/config/fileUploadSavePath_windows.properties";
		if(isLinux()){
			path="/config/fileUploadSavePath_linux.properties";
		}
		InputStream in=FileUploadConstants.class.getResourceAsStream(path);
		if(in!=null){
			prop=new Properties();
			try {
				prop.load(in);
				EXCELPATH_USER=prop.getProperty("EXCELPATH_USER");
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static String getPropValue(String key){
		String path="/config/fileupload/fileUploadSavePath_windows.properties";
		String val = null;
		if(isLinux()){
			path="/config/fileupload/fileUploadSavePath_linux.properties";
		}
		InputStream in=FileUploadConstants.class.getResourceAsStream(path);
		if(in!=null){
			prop=new Properties();
			try {
				prop.load(in);
				val = prop.getProperty(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return val;
	}
	
	public  static boolean isLinux(){
		String osType = System.getProperties().getProperty("os.name").toLowerCase();
		String xmlPath="";
		if(osType.startsWith("windows")){
			return false;
		}
		else{
			return true;
		}
	}
}

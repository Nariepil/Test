package com.cy.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.PropertyPlaceholderHelper;

/**
 * 配置文件帮助类
 * 可以在多个配置文件之间使用占位符
 * 便于配置项统一管理
 * @author liuqiang
 *
 */
public class FreeUrlConfigHelper {
	
	private Logger logger = LoggerFactory.getLogger(FreeUrlConfigHelper.class);
	
	PropertyPlaceholderHelper helper = null;
	Properties props = null;
	
	private FreeUrlConfigHelper(){
		String[] configLocation = new String[]{
				"config/freeUrl/freeUrl.properties"
		};
		init(configLocation);
	}
	
	private FreeUrlConfigHelper(String [] configs){
		init(configs);
	}
	
	private void init(String[] configLocation){
		
		props = new Properties();
		try {
			for(String loc:configLocation){
				props.load(new ClassPathResource(loc).getInputStream());
			}
		} catch (IOException e) {
			logger.error("配置文件位置不存在", e);
		}
		helper = new PropertyPlaceholderHelper("${", "}");
	}
	
	private static class SingletonInstance{
		private static FreeUrlConfigHelper c = new FreeUrlConfigHelper();
	}
	
	public static FreeUrlConfigHelper getInstance(){
		return SingletonInstance.c;
	}


	public List<String> getValues(){
		Set<Object> keySet = props.keySet();
		List<String> rst = new ArrayList<String>();
		for(Object key : keySet){
			String value = (String)props.get(key);
			rst.add(value);
		}
		return rst;
	}
}

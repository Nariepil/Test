package com.cy.base;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author dongao 此为“开关类”
 * 
 * v1.0
 */
public class MySwitch {
	private MySwitch(){}
	public static volatile boolean cacheEnable = true;//volatile, 声明变量值的一致性,但不具备互斥性；static,声明变量的唯一性。
	public static volatile boolean otherCacheEnable=true;
	public static volatile boolean readWriteEnable = true;
	public static volatile boolean browseLimitEnable = false; //浏览访问限制过滤器开关
	public static volatile boolean developModeEnable = false;//正式环境建议设置为false
	public static String getHostAddress(){
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return  addr.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "获取不到主机ip地址";
		}
	}
	public static String getHostName(){
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return  addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "获取不到主机名称";
		}
	}
    public static String getAppInfo(){
    	return "主机ip:"+MySwitch.getHostAddress()+",主机名称:"+MySwitch.getHostName();
    }
}

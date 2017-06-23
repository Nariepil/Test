package com.cy.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cy.base.MySearchFilter;



/**
 * 全局常量
 * 
 * @author dongao
 * 
 */
public abstract class Constants {
	/** 搜索字符串前缀 */
	public static final String SEARCH_PREFIX = "search_";
	/** 排序字符串前缀 */
	public static final String ORDER_PREFIX = "order_";
	/**UTF-8编码*/
	public static final String UTF8 = "UTF-8";
	public static final String OpenKey="Te@cherMe";
	public static final Long SLOW_SECOND= 5000l;//default 5秒
	/** cookie.domain*/
	public static final String COOKIEDOMAIN= "dongao.com";
	/** cookie.path*/
	public static final String COOKIEPATH= "/";
	
	/**学分、学时计算被除数:分钟*/
	public static final double DIVIDE= 45;
	/**学分、学时计算被除数:秒*/
	public static final double DIVIDE_SECON= 45*60;
	
	public static final int IndexCreditpx=428;//px
	
	public static final int IndexCreditSteppx=130;//px
	
	/** 一小时多少分钟 */
	public static final long MINUTES_IN_HOUR = 60L;
	/** 一分钟多少秒 */
	public static final long SECONDS_IN_MINUTE = 60L;
	/** 一小时多少秒 */
	public static final long SECONDS_IN_HOUR = SECONDS_IN_MINUTE * MINUTES_IN_HOUR;
	/** 一秒有多少毫秒 */
	public static final long MILLISECONDS_IN_SECOND = 1000;
	
	/** 一分钟有多少毫秒 */
	public static final long MILLISECONDS_IN_MINUTE = SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND;
	
	/**是否有效 eq 字符串*/
	public static final String SEARCH_EQ_IS_VALID=SEARCH_PREFIX+MySearchFilter.Operator.EQ+"_isValid";
	
	public static PropertiesLoader systemPropertiesLoader = new PropertiesLoader("classpath:/config/system/systemParam_" + (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1 ? "windows" : "linux") + ".properties");
	
	public static final String TEMPLATE_PATH = systemPropertiesLoader.getProperty("path.template");
	public static final String UPLOAD_IMG_SIZE = systemPropertiesLoader.getProperty("upload.img.size");
    
   	/**用户注册密码 */
    public static final String USER_REGISTER_PASSWORD = "123456";
    
    
    /**
	 * 调用课件接口密钥
	 */
	public static final String CW_SECRET_KEY = "retrainKey";
	
	/**时间间隔 */
	public static final Integer INTERVAL = 5;
	
	/**newAdd  201609	 begin*/
	
   	//当前项目id
	public static final Integer PROJECTID = null;
	//没有后台用户重新跳转的登录action
	//public static final String NOUSER_REDIRECTTO = "/index";
	public static final String NOUSER_REDIRECTTO = "/login";
	//不需要判断后台用户是否登录的url集合
	public static final List<String> freeUserUrl = new ArrayList<String>() {{
	    add("/g_user/gUser");
	    add("/index");
	}};
	
	
	
	/**newAdd  201609	end */
	
	public static enum IS_VALID{
		NO(0, "无效"), YES(1, "有效");
		private Integer value;
		private String description;
		private IS_VALID(Integer value, String description) {
			this.value = value;
			this.description = description;
		}
		public Integer getValue() {
			return this.value;
		}
		public String getDescription() {
			return this.description;
		}
	}
	
	
	

    //课程状态枚举  请勿再修改
    public static enum StatusItem{
        ENABLE(1, "启用"), HIDDEN(0, "停用");
        private Integer value;
        private String description;
        private StatusItem(Integer value, String description) {
            this.value = value;
            this.description = description;
        }
        public Integer getValue() {
            return this.value;
        }
        public String getDescription() {
            return this.description;
        }
        public static String getDescByValue(Integer value) {
        	StatusItem[] status = StatusItem.values();
            for (StatusItem sta : status) {
                if (sta.getValue().equals(value)) {
                    return sta.getDescription();
                }
            }
            return "";
        }
    }
    
		/**补充信息*/
		public static enum ApplyInfo{
			idCard("身份证"),realName("姓名"),mobilephone("联系方式"),area("地区"),userType("用户类型"),idCardShort("15位身份证号"),origin("来源"),
			sex("性别"),nation("族别"),edu("学历"),cardTime("发证时间"),company("工作单位"),grade("技能等级"),address("通讯地址"),qq("QQ"),idType("证件类型"),studyYear("学习年度");
			private String key;
			private String value;
			private ApplyInfo(String value){
				this.key = this.name();
				this.value=value;
			}
			public static ApplyInfo fromString(String key) {
				return Enum.valueOf(ApplyInfo.class, key);
			}
			public String getKey() {
				return this.key;
			}
			public String getValue() {
				return this.value;
			}
		}
	public static PropertiesLoader initPropertiesLoader = new PropertiesLoader("classpath:/config/init/init.properties");
	public static final String SecretId = initPropertiesLoader.getProperty("SecretId"); 	//是由云平台颁发给用户的访问服务所用的密钥 ID
	public static final String SecretKey = initPropertiesLoader.getProperty("SecretKey");
	public static final String AppName = initPropertiesLoader.getProperty("AppName");
	public static final String SPServiceUrl = initPropertiesLoader.getProperty("SPServiceUrl");
	public static final String validDate = initPropertiesLoader.getProperty("validDate");	//账户有效期，单位月
	public static final String SignatureVersion=initPropertiesLoader.getProperty("SignatureVersion");
	public static final String SignatureMethod= initPropertiesLoader.getProperty("SignatureMethod");
	public static final String Version=initPropertiesLoader.getProperty("Version");
	public static final String playUrl=initPropertiesLoader.getProperty("playUrl");
	public static final String PlayType=initPropertiesLoader.getProperty("PlayType");
	public static final String playMode=initPropertiesLoader.getProperty("playMode");
}


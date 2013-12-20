package com.jitong;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jitong.console.domain.User;


public class JitongConstants {
	/**
	 * session中存放hql语句，用于导出excel
	 */
	public static final String SESSION_OBJECT="SESSION_OBJECT";
	public static final int MAX_PAGE_SIZE=20000;
	
	public static final String USER = "user";
	public static final String JT_DATE_FORMAT="yyyy-MM-dd"; 
	public static final String JT_DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	
	public static final String STRING_TRUE="1";
	public static final String STRING_FALSE="0";
	
	public static final int CAL_SCALE=2;//计算结果保留2位小数
	public static final int CAL_ROUND_MODE=BigDecimal.ROUND_FLOOR;//直接指定小数位后面部分

	/**
	 * super user name
	 */
	public static final String ADMIN="admin";
	public static User ADMIN_USER=null;// load from SysCache
	
	public static final String SELECT_BY_ALL = "all";
	public static final String SELECT_BY_DEPT = "dept";
	public static final String SELECT_BY_PERSON = "person";
	
	//------properties load from config.properties-------------------
	public static String SPID;
	
	private static String HOST;
	public static String SMS_SECURITY_CODE_ENABLED;
	
	/**
	 * If the content (String) is longer thant CONTENT_SHOW_MAX_LENGTH, cut the end.
	 */
	public static int CONTENT_SHOW_MAX_LENGTH=200;
	
	public static List<String> YEAR_LIST = new ArrayList();
	public static List<String> MONTH_LIST = new ArrayList();
	public static Map<String,String> YES_NO_MAP = new HashMap<String,String>();
	
	public static Properties properties = null;
	public static void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = JitongConstants.class.getResourceAsStream("/config.properties");
			properties.load(in);
			SPID = getProp("SPID", "1860");
			HOST = getProp("host", "http://localhost/");
			SMS_SECURITY_CODE_ENABLED = getProp("SMS_SECURITY_CODE_ENABLED", "0");
			String maxLen=getProp("CONTENT_SHOW_MAX_LENGTH", "200");
			CONTENT_SHOW_MAX_LENGTH = Integer.parseInt(maxLen);
			
			
			YEAR_LIST.add("%");
			for(int i=2012;i<=2017;i++){
				YEAR_LIST.add(String.valueOf(i));
			}
			MONTH_LIST = new ArrayList<String>();
			MONTH_LIST.add("%");
			for(int i=1;i<=12;i++){
				String iStr;
				if(i<10){
					iStr ="0"+String.valueOf(i);
				}else{
					iStr=String.valueOf(i);
				}
				MONTH_LIST.add(iStr);
			}
			
			YES_NO_MAP.put("", "");
			YES_NO_MAP.put("1", "是");
			YES_NO_MAP.put("0", "否");
			
		} catch (Exception ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProp(String name, String defaultValue) {
		if (properties == null) {
			return null;
		}
		String property = null;
		try {
			property = new String(properties.getProperty(name).getBytes("ISO-8859-1"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			property = properties.getProperty(name);
		} catch (Exception e) {
			property = properties.getProperty(name);
		}

		if (property == null) {
			return defaultValue;
		}
		return property.trim();
	}

	public static void init() {
		loadProps();
	}

	public static String getHOST() {
		if(HOST==null)init();
		return HOST;
	}
	
}

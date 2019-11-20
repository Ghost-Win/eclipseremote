package cn.gucci.util;
/**
 * 
 * @author Administrator
 * 工具类，提供数据库连接和关闭连接的功能
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	private Properties prop;
	private static ConfigManager cf;
	/**
	 * 读取文件信息
	 */
	private ConfigManager() {
		String configFile = "Data.properties";
		InputStream in = ConfigManager.class.getClassLoader().getResourceAsStream(configFile);
		prop = new Properties();
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static synchronized ConfigManager getInstance() {
		if(cf==null) {
			cf=new ConfigManager();
		}
		return cf;
	}
	
	public String  getString(String key) {
		return prop.getProperty(key);
	}
}

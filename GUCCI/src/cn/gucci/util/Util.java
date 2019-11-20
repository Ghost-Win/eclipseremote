package cn.gucci.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 获得数据库的连接
 * @author Administrator
 *
 */
public class Util {
	
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(ConfigManager.getInstance().getString("driver"));
			String url=ConfigManager.getInstance().getString("url");
			conn=DriverManager.getConnection(url,ConfigManager.getInstance().getString("user"),ConfigManager.getInstance().getString("pwd"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭资源
	public static void closeResource(PreparedStatement ps, ResultSet rs,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

package util;

import java.sql.Connection;
import java.sql.DriverManager;

/** 
* @author:  fangrongfu; 
* @version: 1.0;
* @date：        2017年7月18日 上午10:03:48;
*/
public class JdbcConnection {
	public static Connection getConnection(){
		String driver = "com.mysql.jdbc.Driver";//数据库连接驱动
		String url = "jdbc:mysql://localhost:3306/test";//数据连接的路径
		String user = "root";//数据库连接的用户名
		String password = "1234";//数据库连接的密码
		Connection con = null;//创建的连接对象
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getConnectionOne(){
		String driver = "com.mysql.jdbc.Driver";//数据库连接驱动
		String url = "jdbc:mysql://202.114.70.53:3306/finance?useUnicode=true&characterEncoding=utf8";//数据连接的路径
		String user = "root";//数据库连接的用户名
		String password = "irlab2013";//数据库连接的密码
		Connection con = null;//创建的连接对象
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, password);
			//System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}

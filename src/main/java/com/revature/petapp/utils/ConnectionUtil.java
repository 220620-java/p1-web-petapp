package com.revature.petapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// singleton design pattern: makes sure that you can only have one instance of something
// factory design pattern: generates and returns a particular object
public class ConnectionUtil {
	// singleton: private constructor, public static synchronized getter method
	private static ConnectionUtil connUtil;
	//private Properties props;
	
	private ConnectionUtil() {
		// this constructor can be blank if you're not using
		// a properties file for your connection info
		//props = new Properties();
		
		//InputStream propsFile = ConnectionUtil.class.getClassLoader()
				.getResourceAsStream("database.properties");
		//try {
		//	props.load(propsFile);
		//} catch (IOException e) {
		//	e.printStackTrace();
		//}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (connUtil == null) {
			connUtil = new ConnectionUtil();
		}
		return connUtil;
	}
	
	// factory: creates Connection objects and returns them
	public Connection getConnection() {
		// when connecting to the DB, we need:
		// JDBC driver
		// database URL
		// username
		// password
		Connection conn = null;
		
		// using environment variables
		String dbUrl = System.getenv("DB_URL");
		String dbUser = System.getenv("DB_USER");
		String dbPass = System.getenv("DB_PASS");
		
		// using properties file
		// String dbUrl = props.getProperty("url");
		// String dbUser = props.getProperty("usr");
		// String dbPass = props.getProperty("psw");
		
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					dbUrl,
					dbUser,
					dbPass);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}

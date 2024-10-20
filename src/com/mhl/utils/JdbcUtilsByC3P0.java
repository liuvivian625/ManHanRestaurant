package com.mhl.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.ResultSet;

//工具类，用c3p0完成mysql的连接和关闭资源
public class JdbcUtilsByC3P0 {
			
	//连接数据库
	public static Connection getConnection(){
		try {
			ComboPooledDataSource source = new ComboPooledDataSource("hello");	
			Connection connection = source.getConnection();
			System.out.println("连接成功");
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);	
		}
	}
	
	//关闭资源
	public static void close(ResultSet set, Statement statement, Connection connection) {
		try {
			if(set != null) {
				set.close();
			}
			if(statement != null) {
				statement.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);	
		}
		
	}
	
}

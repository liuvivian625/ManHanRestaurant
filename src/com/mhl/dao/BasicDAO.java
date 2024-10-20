package com.mhl.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mhl.utils.JdbcUtilsByC3P0;



//是其它DAO的父类
public class BasicDAO<T> {//使用泛型

	//属性
	private QueryRunner qr = new QueryRunner();
	
	//通用的dml方法
	public int update(String sql, Object... param) { //param可变形参	
		Connection connection = null;
		try {
			connection = JdbcUtilsByC3P0.getConnection();
			int update = qr.update(connection, sql, param);
			return update;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtilsByC3P0.close(null, null, connection);
		}			
	}
	
	//查询多行select结果
	/**
	 * @param sql sql语句可以有?
	 * @param clazz Domain类的Class对象
	 * @param param 可变参数，?的值，可以是多个
	 * @return 根据clazz，返回对应的ArrayList
	 */
	public List<T> queryMulti(String sql, Class<T> clazz, Object... param){
		Connection connection = null;
		try {
			connection = JdbcUtilsByC3P0.getConnection();
			return qr.query(connection, sql, new BeanListHandler<T>(clazz), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtilsByC3P0.close(null, null, connection);
		}
	}
	
	
	//查询单行select结果
	public T querySingle(String sql, Class<T> clazz, Object... param){
		Connection connection = null;
		try {
			connection = JdbcUtilsByC3P0.getConnection();
			return qr.query(connection, sql, new BeanHandler<T>(clazz), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtilsByC3P0.close(null, null, connection);
		}
	}
	
	
	//查询单值select结果
	public Object queryScalar(String sql, Object... param){
		Connection connection = null;
		try {
			connection = JdbcUtilsByC3P0.getConnection();
			return qr.query(connection, sql, new ScalarHandler<Object>(), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtilsByC3P0.close(null, null, connection);
		}
	}
}

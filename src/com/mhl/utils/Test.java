package com.mhl.utils;


public class Test {

	public static void main(String[] args) {
		//测试InputUtility
		System.out.println("Please input an integer");
		int i = InputUtility.readInt();
		System.out.println(i);
		
		//测试JdbcUtilsByC3P0
		JdbcUtilsByC3P0.getConnection();

	}

}

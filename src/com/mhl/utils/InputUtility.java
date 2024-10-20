package com.mhl.utils;

//工具类
//处理各种情况的用户输入

import java.util.*;


public class InputUtility {
	private static Scanner scanner = new Scanner(System.in);
	
	//读取键盘输入的菜单选项，返回该选项
	public static char readMenuSelection() {
		char c;
		for(; ; ) {//无限循环
			String str = readKeyBoard(1, false);
			c = str.charAt(0);
			if(c != '1' && c != '9') {
				System.out.println("输入错误，请重新输入");
			}else {
				break;
			}
		}
		return c;
		
	}
	
	//读取键盘输入的第一个字符，并返回该字符
	public static char readChar() {
		String str = readKeyBoard(1, false);
		return str.charAt(0);
	}
	
	//读取键盘输入的一个字符，如果直接按回车，则返回指定的默认值
	public static char readChar(char defaultValue) {
		String str = readKeyBoard(1, true);
		return (str.length()==0) ? defaultValue : str.charAt(0);
	}
	
	//读取键盘输入的整数类型，长度小于2位
	public static int readInt() {
		int n;
		for(; ; ) {
			String str = readKeyBoard(2, true);
			try {
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}
	
	//读取键盘输入的整数类型或默认值，如果直接按回车，则返回指定的默认值
	public static int readInt(int defaultValue) {
		int n;
		for(; ; ) {
			String str = readKeyBoard(10, true);
			if (str.equals("")) {
				return defaultValue;
			}
			
			try {
				n = Integer.parseInt(str);
				break;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return n;
	}
	
	//读取键盘输入的指定长度的字符串
	public static String readString(int limit) {
		return readKeyBoard(limit, false);
	}
	
	//读取键盘输入的指定长度的字符串或默认值
	public static String readString(int limit, String defaultValue) {
		String str = readKeyBoard(limit, false);
		return str.equals("")? defaultValue : str;
	}
	
	//读取键盘输入的确认选项，Y或N
	public static char readConfirmSelection() {
		System.out.println("请确认，输入你的选择(Y/N)");
		char c;
		for(; ; ) {
			String str = readKeyBoard(1, false).toUpperCase();//转成大写字母
			c = str.charAt(0);
			if(c == 'Y' || c == 'N') {
				break;
			} else {
				System.out.println("输入错误，请重新输入(Y/N)");
			}
		}
		return c;
	}
	
	/* 读取指定长度的字符串，指定输入是否可以为空
	 */
	/**
	 * @param limit 
	 * @param blankReturn 输入是否可以为空，true可以为空，false不可
	 * @return
	 */
	public static String readKeyBoard(int limit, boolean blankReturn) {
		String line = "";
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			
			if(line.length()==0) {
				if(blankReturn) return line;//可以返回空串
				else {
					System.out.println("输入不能为空，请重新输入");
					continue;//不接受空串
				}
			}
			
			if (line.length()>limit) {
				System.out.println("输入长度不能大于"+limit+"，请重新输入");
				continue;//不接受空串
			}
			break;
		}
		return line;
	}

}

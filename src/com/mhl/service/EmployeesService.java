package com.mhl.service;

import com.mhl.dao.EmployeesDAO;
import com.mhl.domain.Employees;

//该类通过调用EmployeesDAO完成对Employees表的各种操作
public class EmployeesService {
	//定义一个EmployeesDAO对象
	private EmployeesDAO employeesDAO = new EmployeesDAO();
	
	//登录验证，可以根据返回对象是否为空来判断登录是否成功
	public Employees getEmpByEmpIDAndPwd(String empId, String empPwd) {
		//注意密码要写md5来转换，因为表中存储的是MD5的密码而非原始密码
		String sql = "select * from employees where emp_id = ? and pwd = md5(?)";
		Employees emp = employeesDAO.querySingle(sql, Employees.class, empId, empPwd);
		return emp;//若没有返回null
	}

}

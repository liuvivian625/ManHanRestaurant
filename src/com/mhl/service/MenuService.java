package com.mhl.service;

import java.util.List;

import com.mhl.dao.MenuDAO;
import com.mhl.domain.Menu;


public class MenuService {
	
	MenuDAO menuDAO = new MenuDAO();
	
	//显示所有菜品
	public List<Menu> list(){
		String sql = "select * from menu";
		List<Menu> list = menuDAO.queryMulti(sql, Menu.class);
		return list;
	}
	
	//点餐,未完成
	public Menu orderFood(int id) {
		return null;
	}
	
	//返回id对应的菜品
	public Menu getMenu(int id) {
		return menuDAO.querySingle("select price from menu where id = ?", Menu.class, id);
	}

	

}

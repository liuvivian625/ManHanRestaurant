package com.mhl.service;

import java.util.List;

import com.mhl.dao.DineTablesDAO;
import com.mhl.domain.DineTables;

public class DineTablesService {
	DineTablesDAO dineTablesDAO = new DineTablesDAO();
	
	//返回所有餐桌信息
	public List<DineTables> list(){
		String sql = "select id, state from dineTables";
		List<DineTables> list = dineTablesDAO.queryMulti(sql, DineTables.class);
		return list;
	}
	
	//根据餐桌号检测餐桌是否存在
	//若不存在返回null
	public DineTables getDineTable(int id) {
		//Tips: 建议写完sql语句先放进mySQL中测试一下
		String sql = "select * from dineTables where id = ?";
		return dineTablesDAO.querySingle(sql, DineTables.class, id);
	}
	
	
	//更新餐桌信息（预定餐桌）
	public boolean orderDineTable(int id, String orderName, String orderTel) {
		String sql = "update dineTables set state = ?, orderName = ?, orderTel = ?"
				+ "where id = ?";
		int row = dineTablesDAO.update(sql, "booked", orderName, orderTel, id);
		return row==1;//即return (row == 1 ? true : false);
		
	}
	
	//更新餐桌状态
	public boolean updateDTState(int id, String state) {
		String sql = "update dineTables set state = ?, orderName = '', orderTel = '' where id = ?";
		int update = dineTablesDAO.update(sql, state, id);
		return update > 0;
	
	}
			
}

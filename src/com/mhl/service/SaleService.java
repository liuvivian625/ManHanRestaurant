package com.mhl.service;

import java.util.List;
import java.util.UUID;

import com.mhl.dao.MultiTableDAO;
import com.mhl.dao.SaleDAO;
import com.mhl.domain.MultiTableBean;
import com.mhl.domain.Sale;

public class SaleService {

	private SaleDAO saleDAO = new SaleDAO();
	private MenuService menuService = new MenuService();
	private DineTablesService dtService = new DineTablesService();
	private MultiTableDAO multiTableDAO = new MultiTableDAO();
	
	
	//点餐:生成账单+更新餐桌状态
	public boolean orderMenu(int menuId, int qty, int dtId) {
		//用UUID生成账单号
		//UUID 是通用唯一标识符（Universally Unique Identifier）
		//randomUUID()方法生成不重复的随机字符串
		String billId = UUID.randomUUID().toString();
		
		//生成账单
		//假设一桌只点一道菜*份数=该桌bill
		String sql1 = "insert into sale values (null,?,?,?,?,?,now(),'unknown','unpaid')";
		int update = saleDAO.update(
				sql1, billId, dtId, menuId, qty, menuService.getMenu(dtId).getPrice()*qty);
		
		if(update<=0) {
			return false;
		}
		
		//更新餐桌状态
		return dtService.updateDTState(dtId, "dining");	
	}
	
	
	//返回所有账单(不含菜品名)
	public List<Sale> listAllSale() {
		return saleDAO.queryMulti("select * from sale", Sale.class);
	}
	
	//返回所有账单(含菜品名，多表查询)
	public List<MultiTableBean> listAllSaleMenuName() {
		return multiTableDAO.queryMulti("select sale.*, name from sale, menu where sale.menu_id = menu.id", MultiTableBean.class);
	}
	
	//查看某个餐桌的账单
	public List<Sale> listSaleByDT(int dtId) {
		return saleDAO.queryMulti("select * from sale where dt_id = ? and state = 'unpaid'", Sale.class, dtId);
	}
	
	
	//支付账单：更新sale表，更新餐桌表
	public boolean payBill(int dtId, String payMethod) {
		//更新sale表
		int update = saleDAO.update("update sale set state = 'paid', method = ? where dt_id = ? ", payMethod, dtId);
		if (update==0) {
			return false;
		}
		//更新餐桌表
		return dtService.updateDTState(dtId, "empty");		
	}
}

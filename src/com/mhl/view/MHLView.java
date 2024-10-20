package com.mhl.view;

import java.util.List;

import com.mhl.domain.DineTables;
import com.mhl.domain.Employees;
import com.mhl.domain.Menu;
import com.mhl.domain.MultiTableBean;
import com.mhl.domain.Sale;
import com.mhl.service.DineTablesService;
import com.mhl.service.EmployeesService;
import com.mhl.service.MenuService;
import com.mhl.service.SaleService;
import com.mhl.utils.InputUtility;

//主界面
public class MHLView {
	//控制是否显示登录界面
	private boolean loop = true; 	
	//获取输入
	private String key = "";
	
	//定义对象
	private EmployeesService empService = new EmployeesService();
	private DineTablesService dtService = new DineTablesService();
	private MenuService menuService = new MenuService();
	private SaleService saleService = new SaleService();
	
	public static void main(String[] args) {
		new MHLView().mainMenu();
	}
	
	//显示所有餐桌状态
	public void listDineTables() {
		List<DineTables> list = dtService.list();
		System.out.println("\n餐桌编号\t\t餐桌状态");
		for(DineTables dt:list) {
			System.out.println(dt);
		}
		System.out.println("显示完毕");
	}
	
	//完成预定餐桌
	public void orderDineTable() {
		System.out.println("===============预定餐桌===============");
		System.out.println("请选择要预定的餐桌编号（-1退出）：");
		int dtId = InputUtility.readInt();
		if (dtId == -1) {
			System.out.println("退出预定");
			return;
		}
		if(InputUtility.readConfirmSelection() == 'Y') {
			DineTables dineTable = dtService.getDineTable(dtId);
			if(dineTable == null) {
				System.out.println("餐桌不存在，退出预定");
				return;
			}
			if(!(dineTable.getState().equals("empty"))) {
				System.out.println("餐桌不为空，退出预定");
				return;
			}
			System.out.println("餐桌可预定，请输入预定人姓名：");
			String orderName = InputUtility.readString(50);
			System.out.println("请输入预定人电话：");
			String orderTel = InputUtility.readString(32);
			if(dtService.orderDineTable(dtId, orderName, orderTel)) {
				System.out.println("预定成功！");
			}else{
				System.out.println("预定失败");
			};			
		}else{
			System.out.println("退出预定");
			//该方法的最后一句不需return，return是为了提前结束
		}		
	}
	
	//显示所有菜品
	public void listMenu() {
		List<Menu> list = menuService.list();
		System.out.println("\n菜品编号\t\t名称\t\t类别\t\t价格");
		for(Menu m: list) {
			System.out.println(m);
		}
		System.out.println("显示完毕");
	}
	
	//点餐：操作餐桌、菜单、流水
	//1. 餐桌号和菜品编号的合理性校验
	//2. 点餐成功修改餐桌状态
	//3. 添加进流水中
	public void orderMenu() {
		System.out.println("===============点餐服务===============");
		System.out.println("请选择要点餐的餐桌编号（-1退出）：");
		int dtId = InputUtility.readInt();
		if (dtId == -1) {
			System.out.println("退出点餐");
			return;
		}
		System.out.println("请选择菜品编号（-1退出）：");
		int menuId = InputUtility.readInt();
		if (menuId == -1) {
			System.out.println("退出点餐");
			return;
		}
		System.out.println("请选择菜品数量（-1退出）：");
		int num = InputUtility.readInt();
		if (num == -1) {
			System.out.println("退出点餐");
			return;
		}
		if(InputUtility.readConfirmSelection() == 'Y') {
			//验证餐桌是否存在
			DineTables dineTable = dtService.getDineTable(dtId);
			if(dineTable == null) {
				System.out.println("餐桌不存在，退出点餐");
				return;
			}
			//验证菜品是否存在
			Menu menu = menuService.getMenu(menuId);
			if(menu == null) {
				System.out.println("菜品不存在，退出点餐");
				return;
			}
			if(saleService.orderMenu(menuId, num, dtId)) {//if true
				System.out.println("点餐成功～");
			}else {
				System.out.println("点餐失败，退出点餐");
			};
		}else {
			System.out.println("退出点餐");
		}
		
	}
	
	//显示/显示账单（给cashier看的账单，不是每桌客人的账单）
	public void listAllSale() {
		List<MultiTableBean> list = saleService.listAllSaleMenuName();
		System.out.println("\n编号\t\t桌号\t\t菜品号\t\t菜品名\t\t数量\t\t金额\t\t日期\t\t\t\t支付方式\t\t账单状态");
		for (MultiTableBean s: list) {
			System.out.println(s);
		}
		System.out.println("显示完毕");
	}
	
	//结账
	public void payBill() {
		System.out.println("===============结账服务===============");
		System.out.println("请选择要结账的餐桌编号（-1退出）：");
		int dtId = InputUtility.readInt();
		if (dtId == -1) {
			System.out.println("退出结账");
			return;
		}
		System.out.println("请输入支付方式（cash/credit/applepay, -1退出）：");
		String payMethod = InputUtility.readString(32);
		if (payMethod.equals("-1")) {
			System.out.println("退出结账");
			return;
		}
		if(InputUtility.readConfirmSelection() == 'Y') {
			//验证餐桌是否存在
			DineTables dineTable = dtService.getDineTable(dtId);
			if(dineTable == null) {
				System.out.println("餐桌不存在，退出结账");
				return;
			}
			//统计该桌消费金额
			List<Sale> list = saleService.listSaleByDT(dtId);
			if(list.isEmpty()) {
				System.out.println("该桌无账单，退出结账");
				return;
			}
			double bill = 0;
			for(Sale s : list) {
				bill = bill+s.getBill();
			}
			System.out.println("第"+dtId+"桌消费"+bill);
			if(saleService.payBill(dtId, payMethod)) {
				System.out.println("结账成功～");
			}else{
				System.out.println("结账失败，退出结账");
			};
		}

	}
	
	//显示主菜单
	public void mainMenu() {
		while(loop) {
			System.out.println("==================满汉楼==================");
			System.out.println("\t\t 1 登录满汉楼");
			System.out.println("\t\t 2 退出满汉楼");
			System.out.println("请输入你的选择：");
			
			key = InputUtility.readString(1);
			switch (key){
			case "1":
				System.out.println("请输入员工号：");
				String empId = InputUtility.readString(50);
				System.out.println("请输入密码：");
				String empPwd = InputUtility.readString(20);
				//去数据库验证该用户是否合法
				//一般会有一张登录表，只包含登录需要的empId, empPwd信息
				//登录验证只需查询登录表
				//登录成功后再查询employees获得详细信息
				Employees emp = empService.getEmpByEmpIDAndPwd(empId, empPwd);
				if (emp != null) {//用户存在
					System.out.println("\n["+emp.getName()+"]登录成功");
					while(loop) {
						System.out.println("===============满汉楼二级菜单===============");
						System.out.println("\t\t1 显示餐桌状态");
						System.out.println("\t\t2 预定餐桌");
						System.out.println("\t\t3 显示所有菜品");
						System.out.println("\t\t4 点餐服务");
						System.out.println("\t\t5 查看账单");
						System.out.println("\t\t6 结账");
						System.out.println("\t\t9 退出满汉楼");
						System.out.println("请输入你的选择：");
						
						key = InputUtility.readString(1);
						switch (key){
						case "1":
							listDineTables();
							break;
						case "2":
							orderDineTable();
							break;
						case "3":
							listMenu();
							break;
						case "4":
							orderMenu();
							break;
						case "5":
							listAllSale();
							break;
						case "6":
							payBill();
							break;
						case "9":
							loop = false;
							break;
						default:
							System.out.println("你的输入有误请重新输入～"); 
							break;
						}
					}		
				} else {
					System.out.println("登录失败"); 
				}
				break;
			case "2":
				loop = false;
				break;
			default:
				System.out.println("你的输入有误请重新输入～"); 
				break;
			}
		}
		System.out.println("你已退出满汉楼系统～"); 
		
	}

}

package com.mhl.domain;

import java.time.LocalDateTime;

//多表查询，比如需在Sale中显示菜品名，则查询Menu和Sale
//法1（本方法）：创建一个新Bean，字段是需要的Menu的字段+需要的Sale的字段， 并创建对应的DAO，但不需Service
//法2:在Sale的字段中创建一个Menu类型的字段

//可以根据业务拆分该MultiTableBean，即创建多个MultiTableBean
public class MultiTableBean {
	//Sale表的字段
	private Integer id;
	private String bill_id;
	private Integer dt_id;
	private Integer menu_id;
	private Integer qty;
	private Double bill;
	private LocalDateTime bill_date;
	private String method;
	private String state;
	//需要的Menu字段：此处需要在Sale表中显示菜品名
	//字段名需和列名一样
	private String name;
	//若遇到多张表有重名字段，可在java程序的sql语句中使用as别名，该字段名则需和别名一样
	//private String menuName;//在SaleService的相关sql中加入name as menuName
	
	
	//底层反射调用的是该无参构造器
	//然后通过set方法给字段赋值
	public MultiTableBean() {
	}

	//不需要该有参构造器
	/*
	public MultiTableBean(Integer id, String bill_id, Integer dt_id, Integer menu_id, Integer qty, Double bill,
			LocalDateTime bill_date, String method, String state, String name) {
		this.id = id;
		this.bill_id = bill_id;
		this.dt_id = dt_id;
		this.menu_id = menu_id;
		this.qty = qty;
		this.bill = bill;
		this.bill_date = bill_date;
		this.method = method;
		this.state = state;
		this.name = name;
	}
	*/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDt_id() {
		return dt_id;
	}

	public void setDt_id(Integer dt_id) {
		this.dt_id = dt_id;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Double getBill() {
		return bill;
	}

	public void setBill(Double bill) {
		this.bill = bill;
	}

	public LocalDateTime getBill_date() {
		return bill_date;
	}

	public void setBill_date(LocalDateTime bill_date) {
		this.bill_date = bill_date;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String getBill_id() {
		return bill_id;
	}

	public void setBill_id(String bill_id) {
		this.bill_id = bill_id;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return id + "\t\t" + dt_id + "\t\t" + menu_id + "\t\t" + name + "\t\t" + qty + "\t\t" + bill + "\t\t" + bill_date
				+ "\t\t" + method + "\t\t" + state;
	}
}

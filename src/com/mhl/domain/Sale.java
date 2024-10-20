package com.mhl.domain;

import java.time.LocalDateTime;

public class Sale {
	
	private Integer id;
	private String bill_id;
	private Integer dt_id;
	private Integer menu_id;
	private Integer qty;
	private Double bill;
	private LocalDateTime bill_date;
	private String method;
	private String state;
	
	
	public Sale() {
	}


	public Sale(Integer id, String bill_id, Integer dt_id, Integer menu_id, Integer qty, Double bill, LocalDateTime bill_date,
			String method, String state) {
		this.id = id;
		this.bill_id = bill_id;
		this.dt_id = dt_id;
		this.menu_id = menu_id;
		this.qty = qty;
		this.bill = bill;
		this.bill_date = bill_date;
		this.method = method;
		this.state = state;
	}



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

	@Override
	public String toString() {
		return id + "\t\t" + dt_id + "\t\t" + menu_id + "\t\t" + qty + "\t\t" + bill + "\t\t" + bill_date
				+ "\t\t" + method + "\t\t" + state;
	}
	
	
	
}

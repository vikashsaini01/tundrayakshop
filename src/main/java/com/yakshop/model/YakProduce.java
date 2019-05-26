package com.yakshop.model;

import java.math.BigDecimal;

public class YakProduce {
	private BigDecimal milkInLitres;
	private int noOfWools;
	public YakProduce(BigDecimal milkInLitres, int noOfWools) {
		super();
		this.milkInLitres = milkInLitres;
		this.noOfWools = noOfWools;
	}
	public BigDecimal getMilkInLitres() {
		return milkInLitres;
	}
	public int getNoOfWools() {
		return noOfWools;
	}
	public void setMilkInLitres(BigDecimal milkInLitres) {
		this.milkInLitres = milkInLitres;
	}
	public void setNoOfWools(int noOfWools) {
		this.noOfWools = noOfWools;
	}
	
	
	
}

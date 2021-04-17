package com.kk.model;

import java.math.BigDecimal;

public class Product {

	 private int id;
	    private String productName;
	    private BigDecimal costPerUnit;
	    private BigDecimal unitQuantity;
	    
	    public Product(int id, String productName, BigDecimal costPerUnit, BigDecimal unitQuantity) {
	        this.id = id;
	        this.productName = productName;
	        this.costPerUnit = costPerUnit;
	        this.unitQuantity = unitQuantity;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public BigDecimal getCostPerUnit() {
	        return costPerUnit;
	    }

	    public BigDecimal getUnitQuantity() {
	        return unitQuantity;
	    }
	
}

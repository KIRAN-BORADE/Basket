package com.kk.model;

import java.math.BigDecimal;

public class BasketItem {

	private int id;
    private BigDecimal requiredQuantity;

    public BasketItem(int id, BigDecimal requiredQuantity) {
        this.id = id;
        this.requiredQuantity = requiredQuantity;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getRequiredQuantity() {
        return requiredQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "id=" + id +
                ", requiredQuantity=" + requiredQuantity +
                '}';
    }
	
}

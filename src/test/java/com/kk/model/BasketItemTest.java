package com.kk.model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class BasketItemTest {

	 @Test
	    public void BasketItemPopulateTest() {

	        final int id = 1;
	        final BigDecimal requiredQty = BigDecimal.ONE;
	        final BasketItem basketItem = new BasketItem(id, requiredQty);

	        assertEquals(id, basketItem.getId());
	        assertEquals(requiredQty, basketItem.getRequiredQuantity());

	    }
	
}

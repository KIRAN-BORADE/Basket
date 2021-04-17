package com.kk.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.kk.exception.InvalidProductException;
import com.kk.model.Basket;
import com.kk.model.BasketItem;
import com.kk.model.Product;
import com.kk.serviceImpl.BasketServiceImpl;

public class BasketSeviceImplTest {

	 private ProductService productService = Mockito.mock(ProductService.class);
	    private BasketService basketService = new BasketServiceImpl(productService);

	    @Test
	    public void checkBucketTest() {

	        final int lemonId = 1;
	        BigDecimal lemonQtyInBasket = BigDecimal.TEN;
	        BigDecimal lemonPrice = new BigDecimal("0.50");
	        BigDecimal lemonUnitQty = BigDecimal.ONE;
	        Product productLemon = new Product(lemonId, "LEMONS", lemonPrice, lemonUnitQty);

	        int orangeId = 2;
	        BigDecimal orangeQtyInBasket = new BigDecimal("150");
	        BigDecimal orangePrice = new BigDecimal("0.80");
	        BigDecimal orangeUnitQty = new BigDecimal("100");
	        Product productOrange = new Product(orangeId, "ORANGES", orangePrice, orangeUnitQty);

	        int bananaId = 3;
	        BigDecimal bananaQtyInBasket =  new BigDecimal("200");
	        BigDecimal bananaPrice = new BigDecimal("1.75");
	        BigDecimal bananaUnitQty = new BigDecimal("100");
	        Product productBanana = new Product(bananaId, "BANANAS", bananaPrice, bananaUnitQty);

	        Basket basket = new Basket();
	        basket.addItem(new BasketItem(lemonId, lemonQtyInBasket));
	        basket.addItem(new BasketItem(orangeId, orangeQtyInBasket));
	        basket.addItem(new BasketItem(bananaId, bananaQtyInBasket));

	        BigDecimal totalCost = basketService.findBasketCost(basket);

	        assertEquals(new BigDecimal("9.70"),totalCost);

	    }

	   
	    
	    @Test
	    public void checkInvalidProductExceptionIsThrownWhenQtyNegativeTest() {

	        Basket basket = new Basket();
	        basket.addItem(new BasketItem(1, BigDecimal.ONE));
	        final BigDecimal price = new BigDecimal("1.50");

	        final Product product = new Product(1, "TEST", price, new BigDecimal("-1")); // Negative Qty
	        when(productService.getProduct(1)).thenReturn(product);

	        basketService.findBasketCost(basket);

	    }

	    @Test
	    public void checkInvalidProductExceptionIsThrownWhenPriceNegativeTest() {

	        Basket basket = new Basket();
	        basket.addItem(new BasketItem(1, BigDecimal.ONE));
	        final BigDecimal price = new BigDecimal("-1.50");

	        final Product product = new Product(1, "TEST", price, BigDecimal.ONE); // Negative Price
	        when(productService.getProduct(1)).thenReturn(product);

	        basketService.findBasketCost(basket);

	    }
	
}

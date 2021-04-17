package com.kk.service;

import java.math.BigDecimal;

import com.kk.model.Basket;

public interface BasketService {

	BigDecimal findBasketCost(Basket basket);
	
}

package com.kk.serviceImpl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kk.exception.BasketItemNotFoundException;
import com.kk.exception.InvalidProductException;
import com.kk.model.Basket;
import com.kk.model.BasketItem;
import com.kk.model.Product;
import com.kk.service.BasketService;
import com.kk.service.ProductService;

public class BasketServiceImpl implements BasketService{

	private static Logger LOGGER = LoggerFactory.getLogger(BasketServiceImpl.class);

    private ProductService productService;

    public BasketServiceImpl(ProductService productService) {
        this.productService = productService;
    }

    public BigDecimal findBasketCost(Basket basket) throws BasketItemNotFoundException {

        if (basket == null) {
            throw new IllegalArgumentException("No basket has been supplied");
        }

        List<BasketItem> basketItems = basket.getBasketItems();

        if (basketItems == null || basketItems.size() == 0) {
            throw new IllegalArgumentException("No items have been supplied in your basket");
        }

        BigDecimal totalCost = BigDecimal.ZERO;

        for (BasketItem basketItem : basketItems) {
            
            Product product = productService.getProduct(basketItem.getId());
            if (product == null) {
                throw new BasketItemNotFoundException("Product Id: " + basketItem.getId() + " not found");
            }
            BigDecimal itemPrice = getItemPrice(product, basketItem);
            totalCost = totalCost.add(itemPrice);
        }

        LOGGER.info("Items processed: " + basketItems.size() + ", total cost: " + totalCost);

        return totalCost;

    }

   

    private BigDecimal getItemPrice(Product product, BasketItem basketItem) {

        // Check product details are okay.

        if (product.getUnitQuantity().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("Required unit quantity less than or equal to zero");
        }

        if (product.getCostPerUnit().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidProductException("Required cost per unit less than or equal to zero");
        }

        BigDecimal itemPrice = product.getCostPerUnit().multiply(basketItem.getRequiredQuantity()).divide(product.getUnitQuantity());

        LOGGER.info("Item being processed: " + product.getProductName() +
                    ", Quantity required: " + basketItem.getRequiredQuantity() +
                    ", Unit Price: " + product.getCostPerUnit() +
                    ", Unit Quantity: " + product.getUnitQuantity() +
                    ", Item Price: <" + itemPrice + ">");

        return itemPrice;

    }
	
}

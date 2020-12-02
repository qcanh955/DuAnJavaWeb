package com.javaweb.models;

import com.javaweb.entities.Product;

public class CartItem {
	private final Product products;
    
	private int quantity;
    private double subTotal;
	public CartItem(Product products) {
		super();
		this.products = products;
		this.quantity = 1;
		this.subTotal = products.getPrice();
		
		
	}
	public Product getProduct() {
        return products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        subTotal = products.getPrice() * quantity;
        return subTotal;
    }
    
    
}

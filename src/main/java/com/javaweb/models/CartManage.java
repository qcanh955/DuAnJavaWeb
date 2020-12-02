package com.javaweb.models;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public class CartManage {
	private static final String SESSION_KEY_SHOPPING_CART = "shoppingCart";

    public Cart getCart(HttpSession session){
        Cart cart = (Cart) session.getAttribute(SESSION_KEY_SHOPPING_CART);

        if(cart == null){
            cart = new Cart();
            setCart(session, cart);
        }

        return cart;
    }

    public void setCart(HttpSession session, Cart cart){
        session.setAttribute(SESSION_KEY_SHOPPING_CART, cart);
    }

    public void removeCart(HttpSession session){
        session.removeAttribute(SESSION_KEY_SHOPPING_CART);
    }
}

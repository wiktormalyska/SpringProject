package com.wiktormalyska.backend.services;

import com.wiktormalyska.backend.model.Cart;
import com.wiktormalyska.backend.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class CartService {
    private final Cart cart = new Cart();

    public Cart getCart() {
        return cart;
    }

    public void addItem(Item item) {
        cart.addItem(item);
    }

    public void removeItem(Long itemId) {
        cart.removeItem(itemId);
    }

    public double getTotal() {
        return cart.getTotal();
    }

    public void clear() {
        cart.clear();
    }
}

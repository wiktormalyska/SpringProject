package com.wiktormalyska.backend.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private Map<Long, CartItem> items = new HashMap<>();

    public void addItem(Item item){
        CartItem cartItem = items.get(item.getId());
        if (cartItem == null){
            cartItem = new CartItem(item, 1);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        items.put(item.getId(), cartItem);
    }

    public void removeItem(Long id){
        int quantity = items.get(id).getQuantity();
        if (quantity == 1){
            items.remove(id);
        } else {
            items.get(id).setQuantity(quantity - 1);
        }
    }

    public double getTotal(){
        if (items.isEmpty()){
            return 0;
        }
        return items.values().stream()
                .mapToDouble(cartItem -> cartItem.getItem().getPrice() * cartItem.getQuantity())
                .sum();
    }
    public void clear(){
        items.clear();
    }
}

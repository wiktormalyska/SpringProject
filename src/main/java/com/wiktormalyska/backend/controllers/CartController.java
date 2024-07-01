package com.wiktormalyska.backend.controllers;

import com.wiktormalyska.backend.model.Item;
import com.wiktormalyska.backend.services.CartService;
import com.wiktormalyska.backend.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable Long itemId) {
        Item item = itemService.getItem(itemId).toItem();
        cartService.addItem(item);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{itemId}")
    public String removeItemFromCart(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart() {
        cartService.clear();
        return "redirect:/cart";
    }

}

package com.wiktormalyska.backend.controllers;

import com.wiktormalyska.backend.model.Cart;
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

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping({"/home", "/"})
public class HomeController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;


    @GetMapping
    public String home(Model model) {
        Cart cart = cartService.getCart();
        Collection<Item> items = new ArrayList<>();
        itemService.getItems().forEach(item -> items.add(item.toItem()));
        model.addAttribute("items", items);
        model.addAttribute("cart", cart);
        return "home";
    }

    @PostMapping("/home-cart/add/{itemId}")
    public String addItemToCart(@PathVariable Long itemId) {
        Item item = itemService.getItem(itemId).toItem();
        cartService.addItem(item);
        return "redirect:/home";
    }

    @PostMapping("/home-cart/remove/{itemId}")
    public String removeItemFromCart(@PathVariable Long itemId) {
        cartService.removeItem(itemId);
        return "redirect:/home";
    }
}
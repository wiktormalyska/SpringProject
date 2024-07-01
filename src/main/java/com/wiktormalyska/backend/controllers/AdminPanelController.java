package com.wiktormalyska.backend.controllers;

import com.wiktormalyska.backend.dto.CreateItemDto;
import com.wiktormalyska.backend.dto.CreateUserDto;
import com.wiktormalyska.backend.model.Item;
import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import com.wiktormalyska.backend.services.ItemService;
import com.wiktormalyska.backend.services.RoleService;
import com.wiktormalyska.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin-panel")
public class AdminPanelController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping
    public String adminPanel(Model model, Authentication authentication) {
        String username = authentication.getName();
        model.addAttribute("items", itemService.getItems());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("username", username);
        return "admin-panel";
    }

    @PostMapping("/items/remove/{itemId}")
    public String removeItem(@PathVariable Long itemId) {
        Item item = itemService.getItem(itemId).toItem();
        itemService.removeItem(item);
        return "redirect:/admin-panel";
    }

    @PostMapping("/users/remove/{userId}")
    public String removeUser(@PathVariable Long userId) {
        User user = userService.getUser(userId).toUser();
        userService.removeUser(user);
        return "redirect:/admin-panel";
    }

    @PostMapping("/items/add")
    public String addItem(CreateItemDto itemDto) {
        Item item = itemDto.toItem();
        itemService.addItem(item);
        return "redirect:/admin-panel";
    }

    @PostMapping("/users/add")
    public String addUser(CreateUserDto userDto) {
        User user = userDto.toUser();
        userService.addUser(user);
        Role role = roleService.getRole(userDto.getRole());
        userService.assignRole(user.getId(), role.getName());
        return "redirect:/admin-panel";
    }
}

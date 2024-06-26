package com.wiktormalyska.backend.controllers;

import com.wiktormalyska.backend.dto.UserDto;
import com.wiktormalyska.backend.model.User;
import com.wiktormalyska.backend.services.RoleService;
import com.wiktormalyska.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserRoleController {
    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping("/{userName}/addRole")
    public ResponseEntity<String> addRoleToUser(@PathVariable String userName, @RequestBody String roleName) {
        try {
            userService.getUser(userName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("User not found");
        }

        try {
            roleService.getRole(roleName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        try {
            UserDto userDto = userService.getUser(userName);
            User user = userDto.toUser();
            userService.assignRole(user.getId(), roleName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role is already assigned to the user");
        }
        return ResponseEntity.ok("Role added to user successfully");
    }

    @DeleteMapping("/{userName}/removeRole")
    public ResponseEntity<String> removeRoleFromUser(@PathVariable String userName, @RequestBody String roleName) {
        try {
            userService.getUser(userName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("User not found");
        }

        try {
            roleService.getRole(roleName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        try {
            UserDto userDto = userService.getUser(userName);
            User user = userDto.toUser();
            userService.removeRole(user.getId(), roleName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Role is not assigned to the user");
        }
        return ResponseEntity.ok("Role removed from user successfully");
    }
}

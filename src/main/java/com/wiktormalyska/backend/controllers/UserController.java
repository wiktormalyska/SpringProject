package com.wiktormalyska.backend.controllers;
import com.wiktormalyska.backend.dto.CreateUserDto;
import com.wiktormalyska.backend.dto.UserDto;
import com.wiktormalyska.backend.model.User;
import com.wiktormalyska.backend.services.RoleService;
import com.wiktormalyska.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public RoleService roleService;


    @GetMapping("/all")
    public ResponseEntity<Collection<UserDto>> getUsers() {
        Collection<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody CreateUserDto userDto) {
        User newUser = new User(
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPassword()
        );
        try {
            userService.addUser(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
        return ResponseEntity.ok("User added successfully");
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto userdto = userService.getUser(userId);
        if (userdto != null) {
            return ResponseEntity.ok(userdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        UserDto userdto = userService.getUser(username);
        if (userdto != null) {
            return ResponseEntity.ok(userdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable Long userId) {
        try {
            userService.getUser(userId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        try {
            UserDto userDto = userService.getUser(userId);
            User user = userDto.toUser();
            userService.removeUser(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
        return ResponseEntity.ok("User removed successfully");
    }

}

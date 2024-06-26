package com.wiktormalyska.backend.services;

import com.wiktormalyska.backend.dao.IRoleRepository;
import com.wiktormalyska.backend.dao.IUserRepository;
import com.wiktormalyska.backend.dto.UserDto;
import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class UserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, IRoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Collection<UserDto> getUsers(){
        Collection<UserDto> userDtos= new ArrayList<>();
        Collection<User> users = userRepository.findAll();
        for (User user : users) {
            UserDto userDto = new UserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Transactional
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
            return new UserDto(user);
        else
            return null;
    }

    @Transactional
    public UserDto getUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return new UserDto(user);
    }

    @Transactional
    public void addUser(User user){
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Optional<User> checkUser = userRepository.findByUsername(user.getUsername());
        if (checkUser.isPresent())
            throw new IllegalArgumentException("User already exists");
        userRepository.save(user);
    }

    @Transactional
    public void removeUser(User user){
        User checkUser = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.deleteById(user.getId());
    }

    @Transactional
    public void assignRole(Long userId, String roleName){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional
    public void removeRole(Long userId, String roleName){
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException("Role not found"));
        user.getRoles().remove(role);
        userRepository.save(user);
    }
}

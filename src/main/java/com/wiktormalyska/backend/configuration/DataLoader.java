package com.wiktormalyska.backend.configuration;

import com.wiktormalyska.backend.dao.IItemRepository;
import com.wiktormalyska.backend.dao.IRoleRepository;
import com.wiktormalyska.backend.dao.IUserRepository;
import com.wiktormalyska.backend.model.Item;
import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        new Role();
        Role adminRole = Role.builder()
                .name("ADMIN")
                .build();
        roleRepository.save(adminRole);

        new Role();
        Role userRole = Role.builder()
                .name("USER")
                .build();
        roleRepository.save(userRole);

        new User();
        User admin = User.builder()
                .username("admin")
                .email("admin@admin")
                .password(passwordEncoder.encode("admin"))
                .roles(List.of(adminRole))
                .build();
        userRepository.save(admin);

        new Item();
        Item item = Item.builder()
                .name("item")
                .description("item description")
                .price(100)
                .build();
        itemRepository.save(item);
    }
}

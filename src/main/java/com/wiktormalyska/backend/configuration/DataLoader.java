package com.wiktormalyska.backend.configuration;

import com.wiktormalyska.backend.dao.IRoleRepository;
import com.wiktormalyska.backend.dao.IUserRepository;
import com.wiktormalyska.backend.model.Role;
import com.wiktormalyska.backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.getRoles().add(adminRole);
        userRepository.save(admin);
    }
}

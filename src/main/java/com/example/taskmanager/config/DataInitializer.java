package com.example.taskmanager.config;

import com.example.taskmanager.entity.Role;
import com.example.taskmanager.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;
    public DataInitializer(RoleRepository rr) { this.roleRepository = rr; }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role r = new Role(); r.setName("ROLE_USER"); roleRepository.save(r);
        }
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role r = new Role(); r.setName("ROLE_ADMIN"); roleRepository.save(r);
        }
    }
}

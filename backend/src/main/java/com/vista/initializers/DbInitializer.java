package com.vista.initializers;

import com.vista.entities.Role;
import com.vista.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Component
public class DbInitializer {
    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void initDb() {

        Role userRole = new Role();
        userRole.setId(1);
        userRole.setName("ROLE_USER");

        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setId(2);
        adminRole.setName("ROLE_ADMIN");

        roleRepository.save(adminRole);
    }
}

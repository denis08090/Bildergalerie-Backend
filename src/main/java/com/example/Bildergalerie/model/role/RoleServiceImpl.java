package com.example.Bildergalerie.model.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Error: Role " + name + " not found."));
    }

    @PostConstruct
    public void initRoles() {
        if (!roleRepository.findByName("CLIENT").isPresent()) {
            Role clientRole = new Role();
            clientRole.setName("CLIENT");
            roleRepository.save(clientRole);
        }
    }

}


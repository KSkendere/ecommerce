package com.kristineskendere.ecommerceapp.services.authServices;

import com.kristineskendere.ecommerceapp.models.authModels.Role;
import com.kristineskendere.ecommerceapp.repositories.authRepositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createNewRole(Role role) {

        return roleRepository.save(role);
    }
}

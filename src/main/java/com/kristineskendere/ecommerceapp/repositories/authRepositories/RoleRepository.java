package com.kristineskendere.ecommerceapp.repositories.authRepositories;

import com.kristineskendere.ecommerceapp.models.authModels.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String role);
}

package com.kristineskendere.ecommerceapp.repositories.authRepositories;

import com.kristineskendere.ecommerceapp.models.authModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}

package com.gulnazagivetova.field_of_dreams.field_of_dreams.dao;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);
}

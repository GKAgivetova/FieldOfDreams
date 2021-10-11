package com.gulnazagivetova.field_of_dreams.field_of_dreams.dao;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.name = ?1")
    public Role findByName(String name);
}

package com.gulnazagivetova.field_of_dreams.field_of_dreams.service;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.RoleRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.UserRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Role;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void saveUserWithDefaultRole(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        userRepository.save(user);
    }

    public void save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public Long getId(String email) {
        User user = userRepository.findByEmail(email);
        return user.getId();
    }
}

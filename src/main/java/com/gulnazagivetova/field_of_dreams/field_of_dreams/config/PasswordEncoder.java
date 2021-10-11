package com.gulnazagivetova.field_of_dreams.field_of_dreams.config;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Char;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static void main(String[] args) {
//        Char c = new Char("ergfer");
//        System.out.println(c.getChars1());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "agivetova2021";
//        String rawPassword = "agivetova123";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}

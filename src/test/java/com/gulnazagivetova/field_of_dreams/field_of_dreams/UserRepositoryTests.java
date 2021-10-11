package com.gulnazagivetova.field_of_dreams.field_of_dreams;

import static org.assertj.core.api.Assertions.assertThat;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.RoleRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.UserRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Role;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("alex2@gmail.com");
        user.setPassword("alex22021");
        user.setName("Alexander");
        user.setSurname("Hebb");

        User saveUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, saveUser.getId());

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        String email = "alex@gmail.com";

        User user = userRepository.findByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testAddRoleToNewUser() {
        User user = new User();
        user.setEmail("agivetova@gmail.com");
        user.setPassword("agivetova123");
        user.setName("Ivan");
        user.setSurname("Ivan");

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);

    }

    @Test
    public void testAddRolesToExistingUser() {
        User user = userRepository.findById(1L).get();

        Role roleUser = roleRepository.findByName("User");
        user.addRole(roleUser);

        Role roleAdmin = new Role(2L);
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

}

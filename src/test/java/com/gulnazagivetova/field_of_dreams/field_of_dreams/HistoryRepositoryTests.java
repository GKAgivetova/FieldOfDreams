package com.gulnazagivetova.field_of_dreams.field_of_dreams;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.HistoryRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.UserRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.WordRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.History;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.User;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class HistoryRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateHistory() {
        History history = new History();
        history.setQuestion("Что у рыбы сарган зеленого цвета");
        history.setAnswer("кости");
        history.setWon(true);

        Word word = wordRepository.findById(3L).get();
        history.addWord(word);

        String email = "gkagivetova@xyz.com";
        User user = userRepository.findByEmail(email);

        user.addHistoryToUser(history);

        User savedUser = userRepository.save(user);

//        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
}

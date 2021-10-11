package com.gulnazagivetova.field_of_dreams.field_of_dreams;

import static org.assertj.core.api.Assertions.assertThat;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.dao.WordRepository;
import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class WordRepositoryTests {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateWord() {
        Word word = new Word();
        word.setQuestion("Что у рыбы сарган зеленого цвета");
        word.setAnswer("кости");

        Word saveWord = wordRepository.save(word);

        Word existWord = entityManager.find(Word.class, saveWord.getId());

        assertThat(existWord.getQuestion()).isEqualTo(word.getQuestion());
    }
}

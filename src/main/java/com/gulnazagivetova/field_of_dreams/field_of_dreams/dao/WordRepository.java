package com.gulnazagivetova.field_of_dreams.field_of_dreams.dao;

import com.gulnazagivetova.field_of_dreams.field_of_dreams.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}

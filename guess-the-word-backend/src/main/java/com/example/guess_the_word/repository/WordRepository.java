package com.example.guess_the_word.repository;

import com.example.guess_the_word.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
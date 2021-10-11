package com.gulnazagivetova.field_of_dreams.field_of_dreams.entity;

import javax.persistence.*;

@Entity
@Table(name = "questions_answers")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, length = 200, name = "question")
    private String question;

    @Column(nullable = false, length = 45, name = "answer")
    private String answer;

    public Word() {
    }

    public Word(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

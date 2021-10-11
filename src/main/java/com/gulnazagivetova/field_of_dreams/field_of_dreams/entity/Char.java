package com.gulnazagivetova.field_of_dreams.field_of_dreams.entity;

public class Char {
    private Long id;
    private String chars1;
    private String answer;
    private String chars2;

    public Char() {
    }

    public Char(String chars1) {
        this.chars1 = chars1;
    }

    public Char(Long id) {
        this.id = id;
    }

    public String getChars1() {
        return chars1;
    }

    public void setChars1(String chars1) {
        this.chars1 = chars1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChars2() {
        return chars2;
    }

    public void setChars2(String chars2) {
        this.chars2 = chars2;
    }
}

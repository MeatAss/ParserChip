package com.parserChip.domain;

public class SearchMessage {

    private String name;

    public SearchMessage() {
    }

    public SearchMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
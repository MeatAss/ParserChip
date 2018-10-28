package com.parserChip.domain;

public class SearchMessage {

    private String searchItem;

    public SearchMessage() {
    }

    public SearchMessage(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }
}
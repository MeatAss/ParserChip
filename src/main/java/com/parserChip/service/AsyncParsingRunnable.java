package com.parserChip.service;

import com.parserChip.domain.Parser;

public class AsyncParsingRunnable implements Runnable  {

    private Parser parser;
    private String search;

    public AsyncParsingRunnable(Parser parser) {
        this.parser = parser;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public void run() {
        parser.getChips(search);
    }
}

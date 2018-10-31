package com.parserChip.service;

import com.parserChip.domain.Chip;
import com.parserChip.domain.Parser;
import com.parserChip.interfaces.ParsingListener;

import java.util.ArrayList;
import java.util.List;

public class AsyncParsingRunnable implements Runnable {

    private Parser parser;
    private String search;
    private List<Chip> chips;

    public List<Chip> getChips() {
        return chips;
    }

    public String getSearch() {
        return search;
    }

    public AsyncParsingRunnable(Parser parser, List<Chip> chips) {
        this.parser = parser;
        this.chips = chips;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public void run() {
        List<Chip> newChips = parser.getChips(search);
        synchronized(chips)
        {
            chips.addAll(newChips);
        }
    }
}

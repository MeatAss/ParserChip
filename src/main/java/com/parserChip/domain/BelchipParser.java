package com.parserChip.domain;

import java.util.List;

public class BelchipParser extends Parser {

    private static final String path = "https://belchip.by/search/";
    private static final String queryName = "query";
    private static final String address = "https://belchip.by/search/";
    private static final String mainClass = "cat-item";
    private static final String nameClass = "h3 a";
    private static final String costClass = ".denoPrice";

    public BelchipParser() {
    }

    @Override
    public List<Chip> getChips(String search){
        return getListChip(
                getMainElements(
                        getHTML(path, queryName, search, false), mainClass),
                nameClass, address, costClass);
    }
}

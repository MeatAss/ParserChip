package com.parserChip.domain;

import org.jsoup.select.Elements;

import java.util.List;

public class RuChipdipParser extends Parser {

    private static final String path = "https://www.ru-chipdip.by/search?searchtext=";
    private static final String queryName = "searchtext";
    private static final String address = "https://www.ru-chipdip.by";
    private static final String mainClass = "with-hover";
    private static final String nameClass = ".link";
    private static final String costClass = ".price";

    public RuChipdipParser() {
    }

    @Override
    public List<Chip> getChips(String search){
        return getListChip(
                getMainElements(
                        getHTML(path, queryName, search, true), mainClass),
                nameClass, address, costClass);
    }
}

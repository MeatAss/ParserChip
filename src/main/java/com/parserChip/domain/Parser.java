package com.parserChip.domain;

import com.parserChip.interfaces.IParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Parser implements IParser {

    public abstract List<Chip> getChips(String search);

    @Override
    public Document getHTML(String path, String queryName, String queryParam, boolean isGet) {
        Document doc = null;

        try {
            Connection data = Jsoup.connect(path)
                    .data(queryName, queryParam);
            doc = isGet ? data.get() : data.post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    public Elements getMainElements(Document document, String className) {
        return document.getElementsByClass(className);
    }

    @Override
    public List<Chip> getListChip(Elements elements, String nameClass, String linkPath, String cost) {
        List<Chip> chips = new ArrayList<Chip>();

        elements.forEach(item -> chips.add(
                new Chip(item.select(nameClass).first().text(),
                        linkPath + item.select(nameClass).first().attr("href"),
                        item.select(cost).first().text()
                )
        ));

        return chips;
    }
}

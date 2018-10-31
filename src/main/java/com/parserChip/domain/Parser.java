package com.parserChip.domain;

import com.parserChip.interfaces.IParser;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Parser implements IParser {

    public abstract List<Chip> getChips(String search);

    public abstract ChipInformation getChipInformation(String address, String id);

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
    public Document getHTML(String path) {
        Document doc = null;

        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    @Override
    public Element getElementByQuery(Document html, String query) {
        Elements select = html.select(query);
        return select == null ? null : select.first();
    }

    @Override
    public Elements getMainElements(Document document, String className) {
        if (document == null)
            return null;
        return document.getElementsByClass(className);
    }

    @Override
    public List<Chip> getListChip(Elements elements, String nameClass, String linkPath, String cost) {
        List<Chip> chips = new ArrayList<Chip>();

        if (elements == null)
            return chips;

        elements.forEach(item -> chips.add(
                new Chip(item.select(nameClass).first().text(),
                        linkPath + item.select(nameClass).first().attr("href"),
                        item.select(cost).first().text()
                )
        ));

        return chips;
    }
}

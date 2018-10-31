package com.parserChip.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

public class BelchipParser extends Parser {

    private static final String path = "https://belchip.by/search/";
    private static final String queryName = "query";
    private static final String address = "https://belchip.by/product/";
    private static final String mainClass = "cat-item";
    private static final String nameClass = "h3 a";
    private static final String costClass = ".denoPrice";
    private static final String imgSrc = ".left-full a img";
    private static final String informationQuery = ".full-l";

    public BelchipParser() {
    }

    @Override
    public ChipInformation getChipInformation(String address, String id) {
        Document html = getHTML(address);

        String imgSrcTemp = "";
        String informationTemp = "";

        Element elementByQuery = getElementByQuery(html, imgSrc);
        if (elementByQuery != null)
            imgSrcTemp = "https://belchip.by/" + elementByQuery.attr("src");

        elementByQuery = getElementByQuery(html, informationQuery);
        if (elementByQuery != null)
            informationTemp = getElementByQuery(html, informationQuery).text();


        return new ChipInformation(imgSrcTemp,
                informationTemp,
                id);
    }

    @Override
    public List<Chip> getChips(String search){
        return getListChip(
                getMainElements(
                        getHTML(path, queryName, search, false), mainClass),
                nameClass, address, costClass);
    }


}

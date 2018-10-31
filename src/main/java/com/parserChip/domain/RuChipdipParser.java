package com.parserChip.domain;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class RuChipdipParser extends Parser {

    private static final String path = "https://www.ru-chipdip.by/search?searchtext=";
    private static final String queryName = "searchtext";
    private static final String address = "https://www.ru-chipdip.by";
    private static final String mainClass = "with-hover";
    private static final String nameClass = ".link";
    private static final String costClass = ".price";
    private static final String imgSrc = ".item__image_medium_wrapper span img";
    private static final String informationQuery = "#section_0 div p";

    public RuChipdipParser() {
    }

    @Override
    public ChipInformation getChipInformation(String address, String id) {
        Document html = getHTML(address);

        String imgSrcTemp = "";
        String informationTemp = "";

        Element elementByQuery = getElementByQuery(html, imgSrc);
        if (elementByQuery != null)
            imgSrcTemp = elementByQuery.attr("src");

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
                        getHTML(path, queryName, search, true), mainClass),
                nameClass, address, costClass);
    }
}

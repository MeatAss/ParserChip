package com.parserChip.service;

import com.parserChip.domain.Chip;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.util.UriUtils;

@Service
public class ParserService {

    public List<Chip> startParse(String search) {
        return parseBelChip(search);
//        return parseChipdip(search);
    }

    private List<Chip> parseBelChip(String search) {
        List<Chip> chips = new ArrayList<Chip>();

        Document code = null;

        try {
            code = Jsoup.connect("https://belchip.by/search/")
                    .data("query", search)
                    .post();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elementsByClass = code.getElementsByClass("cat-item");
        elementsByClass.forEach(item -> chips.add(
                new Chip(item.select("h3 a").first().text(),
                        "https://belchip.by/search/" + item.select("h3 a").first().attr("href"),
                        item.select(".denoPrice").first().text()
                )
        ));

        return chips;
    }


    private List<Chip> parseChipdip(String search) {
        List<Chip> chips = new ArrayList<Chip>();
        Document code = getDocumentByUrl("https://www.ru-chipdip.by/search?searchtext=" + UriUtils.encode(search, "UTF-8"));
        Elements elementsByClass = code.getElementsByClass("with-hover");
        elementsByClass.forEach(item -> chips.add(
                new Chip(item.getElementsByClass("link").first().text(),
                        "https://www.ru-chipdip.by" + item.getElementsByClass("link").first().attr("href"),
                        item.getElementsByClass("price").first().text()
                )
        ));

        return chips;
    }

    private Document getDocumentByUrl(String path)
    {
        Document doc = null;

        try {
            doc = Jsoup.connect(path).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return doc;
    }



}

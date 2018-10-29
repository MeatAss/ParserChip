package com.parserChip.interfaces;


import com.parserChip.domain.Chip;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;

public interface IParser {
    public Document getHTML(String path, String queryName, String queryParam, boolean isGet);

    public Elements getMainElements(Document document, String className);

    public List<Chip> getListChip(Elements elements, String nameClass, String linkPath, String cost);
}

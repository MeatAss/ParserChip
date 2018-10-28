package com.parserChip.controller;

import com.parserChip.domain.Chip;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/main")
    public String greeting(Map<String, Object> model) {

        List<Chip> mainTableItems = new ArrayList<Chip>();
        mainTableItems.add(new Chip("test1", "https://www.ru-chipdip.by/product0/9000079514", 1500));
        mainTableItems.add(new Chip("test2", "https://www.ru-chipdip.by/product/0438003.wra-3", 1500));
        mainTableItems.add(new Chip("test3", "https://www.ru-chipdip.by/product/b-1250t", 1500));
        mainTableItems.add(new Chip("test4", "https://www.ru-chipdip.by/product/siba-160000.3-15", 1500));
        mainTableItems.add(new Chip("test5", "https://www.ru-chipdip.by/product0/9000081619", 1500));
        mainTableItems.add(new Chip("test1", "https://www.ru-chipdip.by/product0/9000079514", 1500));
        mainTableItems.add(new Chip("test2", "https://www.ru-chipdip.by/product/0438003.wra-3", 1500));
        mainTableItems.add(new Chip("test3", "https://www.ru-chipdip.by/product/b-1250t", 1500));
        mainTableItems.add(new Chip("test4", "https://www.ru-chipdip.by/product/siba-160000.3-15", 1500));
        mainTableItems.add(new Chip("test5", "https://www.ru-chipdip.by/product0/9000081619", 1500));
        mainTableItems.add(new Chip("test1", "https://www.ru-chipdip.by/product0/9000079514", 1500));
        mainTableItems.add(new Chip("test2", "https://www.ru-chipdip.by/product/0438003.wra-3", 1500));
        mainTableItems.add(new Chip("test3", "https://www.ru-chipdip.by/product/b-1250t", 1500));
        mainTableItems.add(new Chip("test4", "https://www.ru-chipdip.by/product/siba-160000.3-15", 1500));
        mainTableItems.add(new Chip("test5", "https://www.ru-chipdip.by/product0/9000081619", 1500));
        mainTableItems.add(new Chip("test1", "https://www.ru-chipdip.by/product0/9000079514", 1500));
        mainTableItems.add(new Chip("test2", "https://www.ru-chipdip.by/product/0438003.wra-3", 1500));
        mainTableItems.add(new Chip("test3", "https://www.ru-chipdip.by/product/b-1250t", 1500));
        mainTableItems.add(new Chip("test4", "https://www.ru-chipdip.by/product/siba-160000.3-15", 1500));
        mainTableItems.add(new Chip("test5", "https://www.ru-chipdip.by/product0/9000081619", 1500));

        model.put("mainTableItems", mainTableItems);

        return "main";
    }

}

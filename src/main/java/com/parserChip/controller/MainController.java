package com.parserChip.controller;

import com.parserChip.domain.Chip;
import com.parserChip.domain.SearchMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Map;

@Controller
public class MainController {

    @MessageMapping("/search")
    @SendTo("/topic/chip")
    public Chip needSearch(SearchMessage searchMessage) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Chip("test1", "https://www.ru-chipdip.by/product0/9000079514", 1500);
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }
}

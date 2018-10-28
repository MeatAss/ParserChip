package com.parserChip.controller;

import com.parserChip.domain.Chip;
import com.parserChip.domain.SearchMessage;
import com.parserChip.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private ParserService parserService;

    @MessageMapping("/search")
    @SendTo("/topic/chip")
    public Chip needSearch(SearchMessage searchMessage) throws Exception {
        return parserService.startParse(searchMessage.getSearchItem()).get(0);
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }
}

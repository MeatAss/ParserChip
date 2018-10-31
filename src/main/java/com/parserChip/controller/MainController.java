package com.parserChip.controller;

import com.parserChip.domain.Chip;
import com.parserChip.domain.ChipInformation;
import com.parserChip.domain.SearchMessage;
import com.parserChip.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MainService mainService;

    @MessageMapping("/search")
    @SendTo("/topic/search")
    public List<Chip> needSearch(SearchMessage searchMessage) throws Exception {
        return mainService.startParse(searchMessage.getMessage());
    }

    @MessageMapping("/nextChips")
    @SendTo("/topic/nextChips")
    public List<Chip> nextChips() throws Exception {
        return mainService.getFirstChips();
    }

    @MessageMapping("/information")
    @SendTo("/topic/information")
    public ChipInformation information(SearchMessage searchMessage) throws Exception {
        return mainService.getInformation(searchMessage.getMessage(), searchMessage.getId());
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        return "main";
    }
}

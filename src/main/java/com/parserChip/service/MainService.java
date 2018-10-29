package com.parserChip.service;

import com.parserChip.domain.Chip;
import com.parserChip.domain.RuChipdipParser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    public List<Chip> startParse(String search) {



        return new RuChipdipParser().getChips(search);
    }
}

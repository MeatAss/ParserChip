package com.parserChip.interfaces;

import com.parserChip.domain.Chip;

import java.util.List;

public interface ParsingListener {
    void parsingReceived(List<Chip> chips);
}
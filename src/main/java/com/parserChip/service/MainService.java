package com.parserChip.service;

import com.parserChip.domain.Chip;
import com.parserChip.domain.ChipInformation;
import com.parserChip.domain.ChipsListenerImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService  {

    private ChipsListenerImpl chipsListener;

    public MainService() {
        chipsListener = new ChipsListenerImpl();
    }

    public List<Chip> startParse(String search) {
        return chipsListener.startParse(search);
    }

    public List<Chip> getFirstChips() {
        return chipsListener.getFirstChips();
    }

    public ChipInformation getInformation(String address, String id) {
        return chipsListener.getInformation(address, id);
    }
}

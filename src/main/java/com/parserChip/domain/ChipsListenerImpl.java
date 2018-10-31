package com.parserChip.domain;

import com.parserChip.interfaces.ParsingListener;
import com.parserChip.service.AsyncParsingRunnable;

import java.util.ArrayList;
import java.util.List;

public class ChipsListenerImpl implements ParsingListener {

    private List<Chip> chips;
    private static final int chipsReturnLength = 20;

    public List<Chip> startParse(String search) {
        chips = new ArrayList<Chip>();

        Thread thread1 = createThread(new BelchipParser(), search);
        Thread thread2 = createThread(new RuChipdipParser(), search);

        while (true)
        {
            if (chips.size() > chipsReturnLength)
                return getFirstChips();
            if ((!thread1.isAlive()) && (!thread2.isAlive()))
                return getFirstChips();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void parsingReceived(List<Chip> chips) {
        this.chips.addAll(chips);
    }

    private Thread createThread(Parser parser, String search) {

        AsyncParsingRunnable asyncParsingRunnable = new AsyncParsingRunnable(parser, chips);
        asyncParsingRunnable.setSearch(search);
        Thread thready = new Thread(asyncParsingRunnable);
        thready.setDaemon(true);
        thready.start();

        return thready;
    }

    public List<Chip> getFirstChips()
    {
        int length = chips.size() > chipsReturnLength ? chipsReturnLength : chips.size();
        List<Chip> sublist = new ArrayList<>();

        if (length != 0) {
            List<Chip> remove = chips.subList(0, length);
            sublist.addAll(remove);
            remove.clear();
        }

        return sublist;
    }

    public ChipInformation getInformation(String address, String id) {
        Parser parser;

        if (address.contains("ru-chipdip.by"))
            parser = new RuChipdipParser();
        else
            parser = new BelchipParser();

        return parser.getChipInformation(address, id);
    }
}

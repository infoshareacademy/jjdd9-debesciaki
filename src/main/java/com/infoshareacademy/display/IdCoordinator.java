package com.infoshareacademy.display;

import com.infoshareacademy.parser.Event;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class IdCoordinator {
    public Map<Integer, Integer> coordinatingEventResToRealID(List<Event> eventList) {
        AtomicInteger lp = new AtomicInteger(1);
        return eventList.stream().collect(Collectors.toMap(k -> lp.getAndIncrement(),
                v -> v.getId()));
    }
}

package com.example.todolist.event;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<AbstractEvent> events = new ArrayList<>();
    public void add(AbstractEvent event) {
        if(hasScheduleConflictWith(event)) {
            return;
        }
        this.events.add(event);
    }

    public void printAll() {
        events.forEach(Event::print);
    }
    public void printBy(EventType type){
        events.stream()
//				.filter(event -> each instanceof Meeting)
                .filter(event -> event.support(type))
//				.collect(Collectors.toList());
                .forEach(Event::print);
    }

    private boolean hasScheduleConflictWith(AbstractEvent event) {
        return events.stream()
                .anyMatch(each ->
                        (event.getStartDate().isBefore(each.getEndDate()) && event.getEndDate().isBefore(each.getEndDate()))
                );
    }
}

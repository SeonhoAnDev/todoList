package com.example.todolist.event;

import com.example.todolist.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class NoDisturbance extends AbstractEvent {
    public NoDisturbance(int id, String title,
                         ZonedDateTime startDate, ZonedDateTime endDate) {
        super(id, title, startDate, endDate);
    }

    @Override
    protected void update(AbstractAuditableEvent update) {

    }

    @Override
    public void print() {
    }

    @Override
    public boolean support(EventType type) {
        return false;
    }
}

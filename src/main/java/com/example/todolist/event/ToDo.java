package com.example.todolist.event;

import com.example.todolist.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class ToDo extends AbstractEvent {
    private String description;

    public ToDo(int id, String title,
                 ZonedDateTime startDate, ZonedDateTime endDate,
                 String description) {
        super(id, title, startDate, endDate);
        this.description = description;
    };

    @Override
    public void print() {
        System.out.printf("[タスク] %s : %s%n", getTitle(), description);
    }

    @Override
    public boolean support(EventType type){
        return type == EventType.TO_DO;
    }

    @Override
    protected void update(AbstractAuditableEvent update) {

    }
}

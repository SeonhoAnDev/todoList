package com.example.todolist.event;

import com.example.todolist.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class OutOfOffice extends AbstractEvent {
    public OutOfOffice(int id, String title,
                       ZonedDateTime startDate, ZonedDateTime endDate) {
        super(id, title, startDate, endDate);
    }

    @Override
    protected void update(AbstractAuditableEvent update) {
    }

    @Override
    public void print() {
        checkIfDeleted();
        System.out.printf("[外出]　%s : %s ~ %s%n", getTitle(), getStartDate(), getEndDate());
    }

    @Override
    public boolean support(EventType type){
        return type == EventType.OUT_OF_OFFICE;
    }
}

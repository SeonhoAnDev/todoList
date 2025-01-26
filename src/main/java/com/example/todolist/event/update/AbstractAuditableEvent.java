package com.example.todolist.event.update;

import java.time.ZonedDateTime;

public abstract class AbstractAuditableEvent {
    private final String title;
    private final ZonedDateTime startDate;
    private final ZonedDateTime endDate;

    protected AbstractAuditableEvent(String title, ZonedDateTime startDate, ZonedDateTime endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public ZonedDateTime getStartDate() { return startDate; }
    public ZonedDateTime getEndDate() { return endDate; }
}

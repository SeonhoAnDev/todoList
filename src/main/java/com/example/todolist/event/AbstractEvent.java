package com.example.todolist.event;

import com.example.todolist.event.update.AbstractAuditableEvent;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class AbstractEvent implements Event {
    private final int id;
    private String title;

    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private Duration duration;

    private final ZonedDateTime createDate;
    private ZonedDateTime modifiedDate;

    boolean deletedYn;

    protected AbstractEvent(int id, String title,
                            ZonedDateTime startDate, ZonedDateTime endDate) {
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    String.format("開始時は終了時より以前出なければならないです。開始時＝%s、終了時＝%s", startDate, endDate)
            );
        }
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = Duration.between(startDate, endDate);

        ZonedDateTime now = ZonedDateTime.now();
        this.createDate = now;
        this.modifiedDate = now;

        this.deletedYn = false;
    }

    public void validateAndUpdate(AbstractAuditableEvent update) {
        if(deletedYn == true) {
            throw new RuntimeException("すでに削除されたタスクです。");
        }
            defaultUpdate(update);
            update(update);
    }

    void checkIfDeleted() { if (deletedYn) { throw new RuntimeException("すでに削除されたタスクです。"); } }

    private void defaultUpdate(AbstractAuditableEvent update) {
        this.title = update.getTitle();
        this.startDate = update.getStartDate();
        this.endDate = update.getEndDate();
        this.duration = Duration.between(this.startDate, this.endDate);
        this.modifiedDate = ZonedDateTime.now();
    }

    protected  abstract void update(AbstractAuditableEvent update);

    public void delete(boolean deletedYn) {
        this.deletedYn = deletedYn;
    }

    public String getTitle() {
        return this.title;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

}


package com.example.todolist.event;

import com.example.todolist.event.update.AbstractAuditableEvent;
import com.example.todolist.event.update.UpdataMeeting;

import java.time.ZonedDateTime;
import java.util.Set;

public class Meeting extends AbstractEvent {
    private Set<String> participants;
    private String meetingRoom;
    private String agenda;

    public Meeting(int id, String title,
                   ZonedDateTime startDate, ZonedDateTime endDate,
                   Set<String> participants, String meetingRoom, String agenda) {
        super(id, title, startDate, endDate);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    @Override
    public void print(){
        System.out.printf("[ミーティング]　%s : %s%n", getTitle(), agenda);
    }

    @Override
    public boolean support(EventType type) {
        return type == EventType.MEETING;
    }

    public void update(){
        
    }

    @Override
    protected  void update(AbstractAuditableEvent update) {
        UpdataMeeting meetingUpdate = (UpdataMeeting) update;

        this.participants = meetingUpdate.getParticipants();
        this.meetingRoom = meetingUpdate.getMeetingRoom();
        this.agenda = meetingUpdate.getAgenda();
    }
}

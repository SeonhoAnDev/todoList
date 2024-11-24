package com.example.todolist.event.update;

import java.time.ZonedDateTime;
import java.util.Set;

public class UpdataMeeting extends AbstractAuditableEvent {
    private final Set<String> participants;
    private final String meetingRoom;
    private final String agenda;

    public UpdataMeeting(String title, ZonedDateTime startData, ZonedDateTime endDate,
                            Set<String> participants, String meetingRoom, String agenda) {
        super(title, startData, endDate);

        this.participants = participants;
        this.meetingRoom = meetingRoom;
        this.agenda = agenda;
    }

    public Set<String> getParticipants() { return participants; }
    public String getMeetingRoom() { return meetingRoom; }
    public String getAgenda() { return agenda; }
}

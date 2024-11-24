package com.example.todolist;

import com.example.todolist.event.*;
import com.example.todolist.event.update.UpdataMeeting;
import com.example.todolist.reader.EventCsvReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class TodoListApplication {

	public static void main(String[] args) throws IOException {
		Schedule schedule = new Schedule();
		EventCsvReader csvReader = new EventCsvReader();
		String meetingCsvPath = "/data/meeting.csv";
		List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
		meetings.forEach(schedule::add);
//		HashSet<String> participants = new HashSet<>();
//		participants.add("seonho.An");
//		Meeting meeting1 = new Meeting(
//				1, "meeting1",
//				ZonedDateTime.now(), ZonedDateTime.now().plusHours(1),
//				participants, "meetingRoomA", "aws勉強会"
//		);
//		schedule.add(meeting1);
//
//		Todo todo1 = new Todo(
//				2, "todo1",
//				ZonedDateTime.now(), ZonedDateTime.now().plusHours(4),
//				"java勉強など半年間の計画立て"
//		);
//		schedule.add(todo1);
//
//		Todo todo2 = new Todo(
//				3, "todo2",
//				ZonedDateTime.now().plusHours(5), ZonedDateTime.now().plusHours(4),
//				"java勉強など半年間の計画立て"
//		);
//		schedule.add(todo2);

		Meeting meeting = meetings.get(0);
		meeting.print();

		System.out.println("修正。。");

		meetings.get(0).validateAndUpdate(
				new UpdataMeeting(
						"new title",
						ZonedDateTime.now(),
						ZonedDateTime.now().plusHours(1),
						null,
						"A",
						"new agenda"
				)
		);

		meeting.delete(true);
		System.out.println("削除後修正。。");
		meetings.get(0).validateAndUpdate(
				new UpdataMeeting(
						"new title",
						ZonedDateTime.now(),
						ZonedDateTime.now().plusHours(1),
						null,
						"A",
						"new agenda"
				)
		);

		schedule.printAll();

//		schedule.printBy(EventType.TO_DO);

	}
}
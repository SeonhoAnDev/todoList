package com.example.todolist;

import com.example.todolist.event.*;
import com.example.todolist.event.update.AbstractAuditableEvent;
import com.example.todolist.event.update.UpdataMeeting;
import com.example.todolist.reader.EventCsvReader;
import com.example.todolist.reader.RawCsvReader;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

public class TodoListApplication {

	public static void main(String[] args) throws IOException {
		Schedule schedule = new Schedule();

		EventCsvReader csvReader = new EventCsvReader(new RawCsvReader());

		String meetingCsvPath = "/data/meeting.csv";
		String noDisturbanceCsvPath = "/data/no_disturbance.csv";
		String outOfOfficeCsvPath = "/data/out_of_office.csv";
		String toDoCsvPath = "/data/to_do.csv";

		List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
		meetings.forEach(schedule::add);

		List<NoDisturbance> noDisturbances = csvReader.readNoDisturbances(noDisturbanceCsvPath);
		noDisturbances.forEach(schedule::add);

		List<OutOfOffice> outOfOffices = csvReader.readOutOfOffices(outOfOfficeCsvPath);
		outOfOffices.forEach(schedule::add);

		List<ToDo> todos = csvReader.readToDos(toDoCsvPath);
		todos.forEach(schedule::add);

		Meeting meeting = meetings.get(0);

		meeting.print();
		System.out.println("修正");
		meeting.validateAndUpdate(
				new UpdataMeeting(
						"123",
						ZonedDateTime.now(),
						ZonedDateTime.now().plusHours(1),
						null,
						"123",
						"123"
				)
		);
		meeting.print();
		System.out.println("削除");
		meeting.delete(true);

		schedule.printAll();

	}
}
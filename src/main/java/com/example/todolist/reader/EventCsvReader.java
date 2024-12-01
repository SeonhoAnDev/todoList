package com.example.todolist.reader;

import com.example.todolist.event.Meeting;
import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class EventCsvReader {
    private  final RawCsvReader rawCsvReader;

    public EventCsvReader(RawCsvReader rawCsvReader) {
        this.rawCsvReader = rawCsvReader;
    }

    public List<Meeting> readMeetings(String path) throws IOException {
        List<Meeting> result = new ArrayList<>();

        List<String[]> read = rawCsvReader.readAll(path);
        for(int i = 0; i < read.size(); i++){
            if(skipHeader(i)){
                continue;
            }

            String[] each = read.get(i);

            result.add(
                    new Meeting(
                            Integer.parseInt(each[0]),
                            each[2],
                            ZonedDateTime.of(LocalDateTime.parse(each[6], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Tokyo")
                            ),
                            ZonedDateTime.of(LocalDateTime.parse(each[7], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                                    ),
                                    ZoneId.of("Asia/Tokyo")
                            ),
                            new HashSet<>(Arrays.asList(each[3].split(","))),
                            each[4],
                            each[5]
                    )
            );
        }
        return result;
    }

    private boolean skipHeader(int i) {
        return i == 0;
    }
}

package com.example.todolist.event;

public interface Event {
    void print();

    boolean support(EventType type);
}

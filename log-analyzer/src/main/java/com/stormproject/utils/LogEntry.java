package com.stormproject.utils;


public class LogEntry {
    public String ip;
    public String page;
    public int statusCode;

    public LogEntry(String ip, String page, int statusCode) {
        this.ip = ip;
        this.page = page;
        this.statusCode = statusCode;
    }
}
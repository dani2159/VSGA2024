package com.dns30.vsga2024;

public class Catatan {

    private final String nama;
    private final String timestamp;

    public Catatan(String nama, String timestamp) {
        this.nama = nama;
        this.timestamp = timestamp;
    }

    public String getNama() {
        return nama;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

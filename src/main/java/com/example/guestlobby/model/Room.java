package com.example.guestlobby.model;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Room {
    private final String code;
    private final String host;
    private final Instant createdAt;
    private final CopyOnWriteArrayList<String> players = new CopyOnWriteArrayList<>();

    public Room(String code, String host) {
        this.code = code;
        this.host = host;
        this.createdAt = Instant.now();
        this.players.add(host);
    }

    public String getCode() { return code; }
    public String getHost() { return host; }
    public Instant getCreatedAt() { return createdAt; }
    public List<String> getPlayers() { return players; }
}
package com.example.guestlobby.model;

import java.util.List;

public class RoomView {
    public final String code;
    public final String host;
    public final List<String> players;
    public final int size;

    public RoomView(String code, String host, List<String> players) {
        this.code = code;
        this.host = host;
        this.players = players;
        this.size = players == null ? 0 : players.size();
    }

    public static RoomView from(Room room) {
        return new RoomView(room.getCode(), room.getHost(), room.getPlayers());
    }
}
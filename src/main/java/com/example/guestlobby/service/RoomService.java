package com.example.guestlobby.service;

import com.example.guestlobby.model.Room;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static org.springframework.http.HttpStatus.*;

@Service
public class RoomService {

    private final ConcurrentMap<String, Room> rooms = new ConcurrentHashMap<>();
    private static final char[] ALPHABET = "ABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray(); // no I/O

    private final SecureRandom random = new SecureRandom();

    public Room createRoom(String hostName) {
        String name = safeName(hostName);
        String code;
        do {
            code = generateCode(4);
        } while (rooms.containsKey(code));
        Room room = new Room(code, name);
        rooms.put(code, room);
        return room;
    }

    public Room join(String code, String playerName) {
        String key = code.toUpperCase(Locale.ROOT);
        Room room = rooms.get(key);
        if (room == null) {
            throw new ResponseStatusException(NOT_FOUND, "Room not found");
        }
        String name = safeName(playerName);
        room.getPlayers().add(name);
        return room;
    }

    public Room find(String code) {
        Room room = rooms.get(code.toUpperCase(Locale.ROOT));
        if (room == null) {
            throw new ResponseStatusException(NOT_FOUND, "Room not found");
        }
        return room;
    }

    private String generateCode(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
        return sb.toString();
    }

    private String safeName(String raw) {
        if (!StringUtils.hasText(raw)) {
            throw new ResponseStatusException(BAD_REQUEST, "name is required");
        }
        String s = raw.trim();
        if (s.length() > 30) s = s.substring(0, 30);
        return s;
    }
}
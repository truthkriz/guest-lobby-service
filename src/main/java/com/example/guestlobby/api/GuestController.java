package com.example.guestlobby.api;

import com.example.guestlobby.model.Room;
import com.example.guestlobby.model.RoomView;
import com.example.guestlobby.service.RoomService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/guest", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class GuestController {

    private final RoomService roomService;

    public GuestController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        Map<String, String> res = new HashMap<>();
        res.put("status", "ok");
        return res;
    }

    @PostMapping("/room")
    public RoomView createRoom(@RequestParam("name") String name) {
        Room room = roomService.createRoom(name);
        return RoomView.from(room);
    }

    @PostMapping("/room/{code}/join")
    public RoomView join(@PathVariable("code") String code, @RequestParam("name") String name) {
        Room room = roomService.join(code, name);
        return RoomView.from(room);
    }

    @GetMapping("/room/{code}")
    public RoomView get(@PathVariable("code") String code) {
        Room room = roomService.find(code);
        return RoomView.from(room);
    }
}
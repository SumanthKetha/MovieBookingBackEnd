package com.movie.ticketbooking.contollers;
import com.movie.ticketbooking.models.Screen;
import com.movie.ticketbooking.models.Theatre;
import com.movie.ticketbooking.services.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class ScreenController {
    @Autowired
    private ScreenService screenService;

    @PostMapping("/addScreen")
    public ResponseEntity<?> addScreen(@RequestBody Screen screen) {
        try {
            Screen addedScreen = screenService.createScreen(screen);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Screen added successfully");
            response.put("screen", addedScreen);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/screens")
    public ResponseEntity<?> getAllScreens() {
        try {
            return new ResponseEntity<List<Screen>>(screenService.getAllScreens(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getScreenById/{id}")
    public ResponseEntity<?> getScreenById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Screen>(screenService.getScreenById(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateScreen/{id}")
    public ResponseEntity<?> updateScreen(@RequestBody Screen screen, @PathVariable Integer id) {
        try {
            Screen updatedScreen = screenService.updateScreen(id, screen).get();
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Screen updated successfully");
            response.put("screen", updatedScreen);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/deleteScreen/{id}")
    public ResponseEntity<?> deleteScreen(@PathVariable Integer id) {
        try {
            screenService.deleteScreen(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Screen deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getAllScreensByTheatreId/{theatreId}")
    public Map<String, Object>  getScreensByTheatre(@PathVariable Integer theatreId) {
        List<Screen> screens = screenService.getAllScreensByTheatreId(theatreId);

        Map<String, Object> response = new HashMap<>();

        if (!screens.isEmpty()) {
            Theatre theatre = screens.get(0).getTheatre(); // safe now
            response.put("theatre", theatre);
        } else {
            response.put("theatre", null); // or omit this line if not needed
        }


        List<Map<String, Object>> screenList = screens.stream().map(screen -> {
            Map<String, Object> screenData = new HashMap<>();
            screenData.put("id", screen.getId());
            screenData.put("screenNumber", screen.getScreenNumber());
            return screenData;
        }).collect(Collectors.toList());

        response.put("screens", screenList);
        return response;
    }
}

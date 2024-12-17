package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShowController {
    @Autowired
    private ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<?> addShow(@RequestBody Show show) {
        try {
            System.out.println(show);
            Show addedShow = showService.addShow(show);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Show added successfully");
            response.put("show", addedShow);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/shows")
    public ResponseEntity<?> getAllShows() {
        try {
            return new ResponseEntity<List<Show>>(showService.getAllShowsList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("getShowById/{id}")
    public ResponseEntity<?> getShowById(@PathVariable int id) {
        try {
            return new ResponseEntity<Show>(showService.getShowById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("getAllShowsByMovieId/{id}")
    public ResponseEntity<?> getAllShowsById(@PathVariable int id) {
        try {
            return new ResponseEntity<List<Show>>(showService.getAllShowsByMovieId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateShow/{id}")
    public ResponseEntity<?> updateShow(@RequestBody Show show, @PathVariable int id) {
        try {
            Show updatedShow = showService.updateShow(show, id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Show updated successfully");
            response.put("show", updatedShow);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping("/deleteShow/{id}")
    public ResponseEntity<?> deleteShow(@PathVariable int id) {
        try {
            showService.deleteShow(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Show deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

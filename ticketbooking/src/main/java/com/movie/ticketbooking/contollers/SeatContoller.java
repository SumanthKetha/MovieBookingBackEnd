package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.models.Seat;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.services.SeatService;
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
public class SeatContoller {
    @Autowired
    private SeatService seatService;

    @GetMapping("getAllSeatsByShowId/{id}")
    public ResponseEntity<?> getAllSeatsByShowId(@PathVariable int id) {
        try {
            return new ResponseEntity<List<Seat>>(seatService.getAllSeatsByShowId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addSeats/{showId}")
    public ResponseEntity<?> addSeats(@PathVariable Integer showId, @RequestParam int rows, @RequestParam int seatsPerRow) {
        try {
            String addedSeatsMsg= seatService.addSeat(showId, rows, seatsPerRow);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", addedSeatsMsg);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

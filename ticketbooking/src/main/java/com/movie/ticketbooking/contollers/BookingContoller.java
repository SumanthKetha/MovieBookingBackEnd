package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.models.Booking;
import com.movie.ticketbooking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookingContoller {
    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookTickets")
    public ResponseEntity<?> bookTickets(@RequestParam Integer userId, @RequestParam Integer showId, @RequestParam String seatIds) {
        try {
            List<Integer> seatIdsList= Arrays.stream(seatIds.split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            String result = bookingService.bookSeats(userId, showId, seatIdsList);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/user-bookings/{userId}")
    public ResponseEntity<List<Booking>> getBookingsForUser(@PathVariable Integer userId) {
        List<Booking> bookings = bookingService.getBookingsForUser(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
}

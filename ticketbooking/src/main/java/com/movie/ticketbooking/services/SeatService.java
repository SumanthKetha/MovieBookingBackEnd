package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.Seat;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.repo.SeatRepo;
import com.movie.ticketbooking.repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private ShowRepo showRepo;

    public List<Seat> getAllSeatsByShowId(int showId) {
        Show show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
        return seatRepo.findByShow(show);
    }

    public String addSeat(Integer showId, int rows, int seatsPerRow) {
        Show show = showRepo.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found"));
//        Seat newSeat = new Seat();
        // Save the showtime object
//        newSeat.setSeatNo(seat.getSeatNo());
//        newSeat.setSeatType(seat.getSeatType());
//        newSeat.setPrice(seat.getPrice());
//        newSeat.setIsAvailable(seat.getIsAvailable());
//        newSeat.setShow(show);
//        return seatRepo.save(newSeat);
        // Generate seats for each row
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            char rowName = (char) ('A' + rowIndex);  // Row names: A, B, C, ...

            // Generate seats for the current row
            for (int seatIndex = 1; seatIndex <= seatsPerRow; seatIndex++) {
                Seat seat = new Seat();
                seat.setSeatType(String.valueOf(rowName)); // Set the row (e.g., "A")
                seat.setSeatNo(String.valueOf(seatIndex)); // Set seat number (e.g., "1")
                seat.setShow(show);  // Associate the seat with the show
                seat.setIsAvailable(true);
                seat.setScreen(show.getScreen());
                seatRepo.save(seat);  // Save the seat
            }
        }
        return "Seats have been successfully added to the show with ID " + showId;    }
}

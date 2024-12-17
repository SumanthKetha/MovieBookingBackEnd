package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Booking;
import com.movie.ticketbooking.models.Seat;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.models.User;
import com.movie.ticketbooking.repo.BookRepo;
import com.movie.ticketbooking.repo.SeatRepo;
import com.movie.ticketbooking.repo.ShowRepo;
import com.movie.ticketbooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private SeatRepo seatRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private UserRepo userRepo;

    public String bookSeats(Integer userId, Integer showId, List<Integer> seatIds) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Show show = showRepo.findById(showId).orElseThrow(() -> new RuntimeException("Show not found"));

        // Fetch the seats based on seatIds and check if they are available
        List<Seat> selectedSeats = seatRepo.findAllById(seatIds);
        for (Seat seat : selectedSeats) {
            if (!seat.getIsAvailable()) {
                throw new RuntimeException("Seat " + seat.getSeatNo() + " is already booked");
            }
            seat.setIsAvailable(false);  // Mark seat as unavailable
            seatRepo.save(seat);
        }

        // Create the booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setSeats(selectedSeats);
        bookRepo.save(booking);

        return "Tickets Booked successfully!";
    }


    public List<Booking> getBookingsForUser(Integer userId) {
        List<Booking> bookings = bookRepo.findByUserId(userId);

        // Optionally, populate movie details in each booking
        for (Booking booking : bookings) {
            // Fetching movie details via show relationship (if necessary)
            booking.getShow().getMovieId(); // Assuming Show has a reference to Movie
        }

        return bookings;
    }
}

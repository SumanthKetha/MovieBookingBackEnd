package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Seat;
import com.movie.ticketbooking.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat,Integer> {
    List<Seat> findByShow(Show show);
}

package com.movie.ticketbooking.repo;

import com.movie.ticketbooking.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepo extends JpaRepository<Theatre, Long> {
}

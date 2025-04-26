package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Theatre;
import com.movie.ticketbooking.repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TheatreService {
    @Autowired
    private TheatreRepo theatreRepository;

    public List<Theatre> getAllTheatres() {
        return theatreRepository.findAll();
    }

    public Optional<Theatre> getTheatreById(Long id) {
        return theatreRepository.findById(id);
    }

    public Theatre createTheatre(Theatre theatre) {
        return theatreRepository.save(theatre);
    }

    public Optional<Theatre> updateTheatre(Long id, Theatre updatedTheatre) {
        return theatreRepository.findById(id).map(theatre -> {
            theatre.setName(updatedTheatre.getName());
            theatre.setLocation(updatedTheatre.getLocation());
            theatre.setTotalScreens(updatedTheatre.getTotalScreens());
            return theatreRepository.save(theatre);
        });
    }

    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}

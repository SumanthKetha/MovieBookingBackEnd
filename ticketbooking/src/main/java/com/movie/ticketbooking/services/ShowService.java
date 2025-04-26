package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.Screen;
import com.movie.ticketbooking.models.Show;
import com.movie.ticketbooking.repo.MovieRepo;
import com.movie.ticketbooking.repo.ScreenRepo;
import com.movie.ticketbooking.repo.ShowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    private ShowRepo repo;

    @Autowired
    private  MovieRepo movieRepo;
    @Autowired
    private ScreenRepo screenRepo;

    public Show addShow(Show show) {
        Movie movie = movieRepo.findById(show.getMovie().getId())
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + show.getMovie().getId()));

        Screen screen = screenRepo.findById(show.getScreen().getId())
                .orElseThrow(() -> new RuntimeException("Screen not found with ID: " + show.getScreen().getId()));


        Show show1 = new Show();
        show1.setMovie(movie);
        show1.setScreen(screen);
        show1.setShowTime(show.getShowTime());  // Make sure this is a valid time
        show1.setShowDate(show.getShowDate()); // Make sure the date is valid


// Proceed to save
        return repo.save(show1);

    }

    public List<Show> getAllShowsList() {
        return repo.findAll();
    }

    public Show getShowById(int id) {
        if(!repo.existsById(id)){
            throw new RuntimeException("Show details not found with this "+id);
        }
        return repo.findById(id).get();
    }

    public Show updateShow(Show updatedShow, int id) {
        Optional<Show> optionalShow = repo.findById(id);

        if (optionalShow.isPresent()) {
            updatedShow.setId(id);
            return repo.save(updatedShow);
        } else {
            throw new RuntimeException("Show not found with this " + id);
        }

    }

    public void deleteShow(int id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Show not found with id: " + id);
        }
        repo.deleteById(id);
    }


    public List<Show> getAllShowsByMovieId(Integer movieId) {
        return repo.findAllByMovieId_Id(movieId);
    }
}

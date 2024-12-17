package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.enums.Language;
import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.services.MovieService;
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
public class MovieController {

    @Autowired
    private MovieService service;

    @RequestMapping("/")
    public String greet() {
        return "Hello, Welcome to cinema Ticket Booking Application";
    }

    @GetMapping("/movies")
    public ResponseEntity<?> getAllMovies() {
        try {
            return new ResponseEntity<List<Movie>>(service.getMoviesList(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addMovie")
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        try {
            Movie addedMovie = service.addMovie(movie);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Movie added successfully");
            response.put("movie", addedMovie);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getMovieById/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id) {
        try {
            return new ResponseEntity<Movie>(service.getMovieById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody Movie movie, @PathVariable int id) {
        try {
            Movie updatedMovie = service.updateMovie(movie, id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Movie updated successfully");
            response.put("movie", updatedMovie);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        try {
            System.out.println(id+"idd");
            service.deleteMovie(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Movie deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

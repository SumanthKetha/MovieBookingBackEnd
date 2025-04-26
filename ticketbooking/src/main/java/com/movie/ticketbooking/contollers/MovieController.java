package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.enums.Language;
import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
    public ResponseEntity<?> addMovie( @RequestPart("movie") Movie movie,
                                       @RequestPart("image") MultipartFile imageFile) {
        try {
            // Save image to local folder
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, imageFile.getBytes());

            // Set image path to movie
            movie.setImagePath(filePath.toString());
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
    public ResponseEntity<?> updateMovie(@PathVariable int id,
                                         @RequestPart("movie") Movie movie,
                                         @RequestPart(value = "image", required = false) MultipartFile file) {
        try {
            Movie updatedMovie = service.updateMovie(movie, id,file);
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

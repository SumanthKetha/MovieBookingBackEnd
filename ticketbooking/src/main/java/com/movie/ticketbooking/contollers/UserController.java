package com.movie.ticketbooking.contollers;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.User;
import com.movie.ticketbooking.services.MovieService;
import com.movie.ticketbooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> addUser(@RequestBody User newUser) {
        try {
            User addedUser = userService.addUser(newUser);
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User Registered successfully");
            response.put("user", addedUser);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getUserById/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable int id) {
        try {
            return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateMovie(@RequestBody User user, @PathVariable int id) {
        try {
            User updatedUser = userService.updateUser(user, id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Movie updated successfully");
            response.put("movie", updatedUser);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }

    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User deleted Successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        System.out.println("email"+ email +"password" +password);
        User user = userService.Login(email, password);
        if (user !=null) {
            // Create the response map
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User Logged in successfully");
            response.put("user", user);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
        }
    }
}

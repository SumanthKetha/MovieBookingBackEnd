package com.movie.ticketbooking.services;

import com.movie.ticketbooking.models.Movie;
import com.movie.ticketbooking.models.User;
import com.movie.ticketbooking.repo.MovieRepo;
import com.movie.ticketbooking.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
//    private BCryptPasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User addUser(User newUser) {
//        String hashedPassword=this.passwordEncoder.encode(newUser.getPassword());
//        newUser.setPassword(hashedPassword);
        return userRepo.save(newUser);
    }

    public User updateUser(User updatedUser, int id) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            updatedUser.setId(id);
            return userRepo.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with this " + id);
        }

    }

    public void deleteUser(int id) {
        if (!userRepo.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }

    public User getUserById(int userId) {
        if(!userRepo.existsById(userId)){
            throw new RuntimeException("User details not found with this "+userId);
        }
        return userRepo.findById(userId).get();
    }

    public User Login(String email,String password){
        User user = userRepo.findByEmail(email);
        System.out.println(user);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null; // User not found
    }
}

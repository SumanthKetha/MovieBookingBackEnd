package com.movie.ticketbooking.services;
import com.movie.ticketbooking.models.Screen;
import com.movie.ticketbooking.repo.ScreenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ScreenService {
    @Autowired
    private ScreenRepo screenRepo;

    public List<Screen> getAllScreens() {
        return screenRepo.findAll();
    }

    public Optional<Screen> getScreenById(Integer id) {
        return screenRepo.findById(id);
    }

    public Screen createScreen(Screen screen) {
        return screenRepo.save(screen);
    }

    public Optional<Screen> updateScreen(Integer id, Screen updatedScreen) {
        return screenRepo.findById(id).map(screen -> {
            screen.setScreenNumber(updatedScreen.getScreenNumber());
            screen.setTheatre(updatedScreen.getTheatre());
            return screenRepo.save(screen);
        });
    }

    public void deleteScreen(Integer id) {
        screenRepo.deleteById(id);
    }

    public List<Screen> getAllScreensByTheatreId(Integer theatreId) {
        List<Screen> screens = screenRepo.findByTheatreId(theatreId);
        return  screens;
    }
}

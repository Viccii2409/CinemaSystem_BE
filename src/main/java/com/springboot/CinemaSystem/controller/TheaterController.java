package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.entity.Theater;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    @Autowired
    private TheaterDao theaterDao;

    @GetMapping("")
    public List<Theater> getListTheater(){
        List<Theater> theaters = theaterDao.getAllTheaters();
        if(theaters.isEmpty()){
            throw new NotFoundException("No theaters found.");
        }
        return theaters;
    }

    @GetMapping("/{id}")
    public Theater getTheaterById(@PathVariable("id") int id){
        Theater theater = theaterDao.getTheaterByID(id);
        if(theater != null ){
            return theater;
        }
        throw new NotFoundException("Theater not found with ID: " + id);
    }

}

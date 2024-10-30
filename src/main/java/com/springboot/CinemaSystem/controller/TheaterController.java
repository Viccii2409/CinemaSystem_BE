package com.springboot.CinemaSystem.controller;

import com.springboot.CinemaSystem.dto.TheaterAddDto;
import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.dto.TheaterEditDto;
import com.springboot.CinemaSystem.entity.Theater;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.mapper.TheaterMapper;
import com.springboot.CinemaSystem.service.FileStorageService;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    @Autowired
    private TheaterDao theaterDao;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("")
    public List<TheaterDto> getListTheater(){
        List<TheaterDto> theaters = theaterDao.getAllTheaterDto();
        if(theaters.isEmpty()){
            throw new NotFoundException("No theaters found.");
        }
        return theaters;
    }

    @PutMapping("/{id}/updatestatus")
    public boolean updateStatusTheater(@PathVariable("id") long id){
        return theaterDao.updateStatusTheater(id);
    }

    @PostMapping("/add")
    public TheaterDto addTheater(@ModelAttribute TheaterAddDto theaterAddDto,
                              @RequestParam(value = "file", required = false) MultipartFile file){
        try {
            Theater theater = TheaterMapper.toTheaterAdd(theaterAddDto);
            theater.setStatus(false);
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageService.saveFileFromCloudinary(file);
                theater.setImage(imageUrl);
            }
            Theater saveTheater = theaterDao.addTheater(theater);
            return TheaterMapper.toTheaterDto(theater);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm rạp: " + e.getMessage());
        }

    }

    @PostMapping("/update")
    public TheaterDto editTheater(@ModelAttribute TheaterEditDto theaterEditDto,
                                  @RequestParam(value = "file", required = false) MultipartFile file){
        try {
            Theater theater = TheaterMapper.toTheaterEdit(theaterEditDto);
            Theater theater_old = theaterDao.getTheaterByID(theater.getID());
            theater.setImage(theater_old.getImage());
            theater.setQuantityRoom(theater_old.getQuantityRoom());
            theater.setStatus(theater_old.isStatus());
            theater.setRoom(theater_old.getRoom());
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageService.updateFile(file, theater_old.getImage());
                theater.setImage(imageUrl);
            }
            Theater updateTheater = theaterDao.updateTheater(theater);
            return TheaterMapper.toTheaterDto(theater);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm rạp: " + e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public Theater getTheaterById(@PathVariable("id") long id){
        Theater theater = theaterDao.getTheaterByID(id);
        if(theater != null ){
            return theater;
        }
        throw new NotFoundException("Theater not found with ID: " + id);
    }

}

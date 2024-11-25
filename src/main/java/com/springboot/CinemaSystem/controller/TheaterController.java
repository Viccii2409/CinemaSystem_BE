package com.springboot.CinemaSystem.controller;


import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.mapper.TheaterMapper;
import com.springboot.CinemaSystem.service.FileStorageService;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {

    private TheaterDao theaterDao;
    private FileStorageService fileStorageService;

    @Autowired
    public TheaterController(TheaterDao theaterDao, FileStorageService fileStorageService) {
        this.theaterDao = theaterDao;
        this.fileStorageService = fileStorageService;
    }

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

    @PutMapping("/update")
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


    @DeleteMapping("/{id}/delete")
    public boolean deleteTheater(@PathVariable("id") long id) {
        return theaterDao.deleteTheater(id);
    }

    @GetMapping("/room")
    public List<TheaterRoomDto> getTheaterRoomDtos() {
        List<Theater> theaters = theaterDao.getAllTheaters();
        List<TheaterRoomDto> theaterRoomDtos = new ArrayList<>();
        for(Theater theater : theaters) {
            theaterRoomDtos.add(theater.toTheaterRoomDto());
        }
        return theaterRoomDtos;
    }

//    @GetMapping("/{id}/room")
//    public List<Room> getRoomByTheater(@PathVariable("id") long id){
//        return theaterDao.getRoomByTheater(id);
//    }

    @GetMapping("/typeseat")
    public List<TypeSeat> getTypeSeat() {
        return theaterDao.getAllTypeSeats();
    }

    @GetMapping("/typeroom")
    public List<TypeRoomDto> getTypeRoom() {
        List<TypeRoom> typeRooms = theaterDao.getAllTypeRooms();
        List<TypeRoomDto> typeRoomDtos = new ArrayList<>();
        for(TypeRoom t : typeRooms) {
            typeRoomDtos.add(t.toTypeRoomDto());
        }
        return typeRoomDtos;
    }


    @GetMapping("/{id}")
    public TheaterViewDto getTheaterById(@PathVariable("id") long id){
        Theater theater = theaterDao.getTheaterByID(id);
        if(theater != null ){
            return theater.toTheaterViewDto();
        }
        throw new NotFoundException("Theater not found with ID: " + id);
    }

    @GetMapping("/room/{id}")
    public RoomSeatDto getRoom(@PathVariable("id") long id) {
        return theaterDao.getRoomByID(id).toRoomSeatDto();
    }


    @PostMapping(value = "/{id}/room/add")
    public long addRoom(@PathVariable("id") long id ,@RequestBody Room room) {
        System.out.println(room);
        Theater theater = theaterDao.getTheaterByID(id);
        if(theater == null){
            throw new NotFoundException("Not find theater: " + id);
        }
        room.setTheater(theater);
        theater.getRoom().add(room);
        return theaterDao.addRoom(room).getID();
    }

    @PostMapping(value = "/room/{id}/seat/add")
    public boolean addSeat(@PathVariable("id") long id, @RequestBody List<Seat> seats) {
        Room room = theaterDao.getRoomByID(id);
        if(room == null){
            throw new NotFoundException("Not find room: " + id);
        }
        room.getSeat().clear();
        for(Seat seat : seats){
            seat.setRoom(room);
            room.getSeat().add(seat);
        }
        return theaterDao.updateRoom(room);
    }


    @PutMapping(value = "/room/{id}/seat/update")
    public boolean updateSeat(@PathVariable("id") long id, @RequestBody List<Seat> seats) {
        Room room = theaterDao.getRoomByID(id);
        if(room == null){
            throw new NotFoundException("Not find room: " + id);
        }
        Map<Integer, Map<Integer, Seat>> listSeatMap = room.getSeat().stream()
                .collect(Collectors.groupingBy(
                        Seat::getRowNum,
                        Collectors.toMap(Seat::getSeatNum, seat -> seat)
                ));
        room.getSeat().clear();
        for(Seat seat : seats){
            Map<Integer, Seat> rowSeats = listSeatMap.get(seat.getRowNum());
            Seat currentSeat = (rowSeats != null) ? rowSeats.get(seat.getSeatNum()) : null;

            if(currentSeat != null){
                currentSeat.updateFrom(seat);
                room.getSeat().add(currentSeat);
            }
            else {
                seat.setRoom(room);
                room.getSeat().add(seat);
            }
        }
        return theaterDao.updateRoom(room);

    }

    @PutMapping("/room/{id}/updatestatus")
    public boolean updateStatusRoom(@PathVariable("id") long id) {
//        System.out.println(id);
        return theaterDao.updateStatusRoom(id);
    }

    @PutMapping("/room/update")
    public boolean updateRoom(@RequestBody Room room) {
        Room room1 = theaterDao.getRoomByID(room.getID());
        if(room1 == null) {
            throw new NotFoundException("Not find room");
        }
        TypeRoom typeRoom = theaterDao.getTypeRoomByID(room.getTypeRoom().getID());
        if(typeRoom == null) {
            throw new NotFoundException("Not find typeroom");
        }
        room1.setName(room.getName());
        room1.setTypeRoom(typeRoom);
        room1.setNumRows(room.getNumRows());
        room1.setNumColumn(room.getNumColumn());
        return theaterDao.updateRoom(room1);
    }

    @DeleteMapping("/{id}/room/{roomid}/delete")
    public boolean deleteRoom(@PathVariable("id") long id, @PathVariable("roomid") long roomid) {
        // Lấy Theater từ cơ sở dữ liệu
        Theater theater = theaterDao.getTheaterByID(id);
        if (theater == null) {
            throw new NotFoundException("Theater not found with id: " + id);
        }
        Room roomToRemove = theater.getRoom().stream()
                .filter(room -> room.getID() == roomid)
                .findFirst()
                .orElse(null);

        if (roomToRemove == null) {
            throw new NotFoundException("Room not found with id: " + roomid);
        }
        theater.getRoom().remove(roomToRemove);
        theaterDao.updateTheater(theater);
        return true;
    }
    @GetMapping("/except/{id}")
    public ResponseEntity<List<TheaterExceptDto>> getTheatersExcept(@PathVariable Long id) {
        List<TheaterExceptDto> theaters = theaterDao.getTheatersExcept(id);
        return ResponseEntity.ok(theaters);
    }

}

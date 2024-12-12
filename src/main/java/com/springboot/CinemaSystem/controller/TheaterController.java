package com.springboot.CinemaSystem.controller;


import com.springboot.CinemaSystem.dto.*;
import com.springboot.CinemaSystem.entity.*;
import com.springboot.CinemaSystem.exception.DataProcessingException;
import com.springboot.CinemaSystem.exception.NotFoundException;
import com.springboot.CinemaSystem.service.FileStorageDao;
import com.springboot.CinemaSystem.service.TheaterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/theater")
public class TheaterController {
    private TheaterDao theaterDao;
    private FileStorageDao fileStorageDao;

    @Autowired
    public TheaterController(TheaterDao theaterDao, FileStorageDao fileStorageDao) {
        this.theaterDao = theaterDao;
        this.fileStorageDao = fileStorageDao;
    }

    @GetMapping("/public/all")
    public List<TheaterDto> getAllNameTheater(){
        List<TheaterDto> theaters = theaterDao.getAllTheater().stream()
                .filter(entry -> entry.isStatus())
                .map(entry -> TheaterDto.toNameTheaterDto(entry))
                .collect(Collectors.toList());

        return theaters;
    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @GetMapping("/all")
    public List<TheaterDto> getListTheater(){
        List<TheaterDto> theaters = theaterDao.getAllTheater().stream()
                .map(entry -> TheaterDto.toTheaterDto(entry))
                .collect(Collectors.toList());
        return theaters;
    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @PutMapping("/{id}/updatestatus")
    public boolean updateStatusTheater(@PathVariable("id") long id){
        return theaterDao.updateStatusTheater(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @PostMapping("/add")
    public TheaterDto addTheater(@ModelAttribute TheaterDto dto,
                                 @RequestParam(value = "file", required = false) MultipartFile file){
        try {
            Theater theater = Theater.convertTheaterAddtoTheater(dto);
            theater.setStatus(false);
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageDao.saveFileFromCloudinary(file, "Image/Theater");
                theater.setImage(imageUrl);
            }
            Theater saveTheater = theaterDao.addTheater(theater);
            return TheaterDto.toTheaterDto(saveTheater);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm rạp: " + e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @PutMapping("/update")
    public TheaterDto editTheater(@ModelAttribute TheaterDto dto,
                                  @RequestParam(value = "file", required = false) MultipartFile file
    ){
        try {
            Theater theater = Theater.convertTheaterEdittoTheater(dto);
            Theater theater_old = theaterDao.getTheaterByID(theater.getID());
            theater.setImage(theater_old.getImage());
            theater.setQuantityRoom(theater_old.getQuantityRoom());
            theater.setStatus(theater_old.isStatus());
            theater.setRoom(theater_old.getRoom());
            if(file != null && !file.isEmpty()){
                String imageUrl = fileStorageDao.updateFile(file, theater_old.getImage(), "Image/Theater");
                theater.setImage(imageUrl);
            }
            Theater updateTheater = theaterDao.updateTheater(theater);
            return TheaterDto.toTheaterDto(updateTheater);
        } catch (Exception e) {
            throw new DataProcessingException("Lỗi thêm rạp: " + e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @GetMapping("/{id}")
    public TheaterDto getTheaterById(@PathVariable("id") long id){
        return TheaterDto.toTheaterView(theaterDao.getTheaterByID(id));
    }

    @PreAuthorize("hasAuthority('MANAGER_THEATER')")
    @DeleteMapping("/{id}/delete")
    public boolean deleteTheater(@PathVariable("id") long id) {
        return theaterDao.deleteTheater(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
    @GetMapping("/room")
    public List<TheaterDto> getTheaterRoomDtos() {
        List<TheaterDto> dtos = theaterDao.getAllTheater().stream()
                .map(entry -> TheaterDto.toTheaterRoomDto(entry))
                .collect(Collectors.toList());
        return dtos;
    }

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
    @PutMapping("/room/{id}/updatestatus")
    public boolean updateStatusRoom(@PathVariable("id") long id) {
        return theaterDao.updateStatusRoom(id);
    }

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
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

    @PreAuthorize("hasAnyAuthority('MANAGER_ROOM', 'MANAGER_PRICETICKET')")
    @GetMapping("/typeroom")
    public List<TypeRoomDto> getTypeRoom() {
        List<TypeRoomDto> typeRoomDtos = theaterDao.getAllTypeRooms().stream()
                .map(entry -> TypeRoomDto.toTypeRoomDto(entry))
                .collect(Collectors.toList());
        return typeRoomDtos;
    }

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
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

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
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

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
    @GetMapping("/room/{id}")
    public RoomDto getRoom(@PathVariable("id") long id) {
        return RoomDto.toRoomSeatDto(theaterDao.getRoomByID(id));
    }

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
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

    @PreAuthorize("hasAuthority('MANAGER_ROOM')")
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

    @PreAuthorize("hasAnyAuthority('MANAGER_PRICETICKET', 'MANAGER_SELLING')")
    @GetMapping("/typeseat")
    public List<TypeSeat> getTypeSeat() {
        return theaterDao.getAllTypeSeats();
    }
}
package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    public List<Room> findByTheaterID(long id);
//    public List<Room> findByTheaterIdAndStatus(long theaterId, boolean status); // Lấy phòng của rạp đang hoạt động


}

package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.dto.TheaterExceptDto;
import com.springboot.CinemaSystem.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT new com.springboot.CinemaSystem.dto.TheaterDto" +
            "(t.id, t.name, " +
            "CONCAT(t.address.addressDetail, ', ', t.address.ward.name, ', ', t.address.district.name, ', ', t.address.city.name), " +
            "t.quantityRoom, t.status) " +
            "FROM Theater t")
    List<TheaterDto> getListTheaterDto();


    @Query("SELECT t.name FROM Theater t")
    List<String> findAllTheaterNames();


    @Query("SELECT new com.springboot.CinemaSystem.dto.TheaterExceptDto(t.id, t.name) FROM Theater t WHERE t.id <> :theaterID")
    List<TheaterExceptDto> findAllExcept(@Param("theaterID") Long theaterID);
}
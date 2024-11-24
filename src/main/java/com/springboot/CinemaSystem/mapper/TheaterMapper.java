package com.springboot.CinemaSystem.mapper;

import com.springboot.CinemaSystem.dto.TheaterAddDto;
import com.springboot.CinemaSystem.dto.TheaterDetailDto;
import com.springboot.CinemaSystem.dto.TheaterDto;
import com.springboot.CinemaSystem.dto.TheaterEditDto;
import com.springboot.CinemaSystem.entity.*;

public class TheaterMapper {
    public static Theater toTheaterAdd(TheaterAddDto dto){
        Theater theater = new Theater();
        theater.setName(dto.getName());
        theater.setPhone(dto.getPhone());
        theater.setEmail(dto.getEmail());
        theater.setDescription(dto.getDescription());
        Ward ward = new Ward(dto.getWard());
        District district = new District(dto.getDistrict());
        City city = new City(dto.getCity());
        Address address = new Address(dto.getAddress(), ward, district, city);
        theater.setAddress(address);
        return theater;
    }

    public static Theater toTheaterEdit(TheaterEditDto dto){
        Theater theater = new Theater();
        theater.setId(dto.getId());
        theater.setName(dto.getName());
        theater.setPhone(dto.getPhone());
        theater.setEmail(dto.getEmail());
        theater.setDescription(dto.getDescription());
        Ward ward = new Ward(dto.getWard());
        District district = new District(dto.getDistrict());
        City city = new City(dto.getCity());
        Address address = new Address(dto.getAddress(), ward, district, city);
        theater.setAddress(address);
        return theater;
    }

    public static TheaterDto toTheaterDto(Theater theater){
        String addresss = theater.getAddress().getAddressDetail() + ", "
                + theater.getAddress().getWard().getName() + ", "
                + theater.getAddress().getDistrict().getName() + ", "
                + theater.getAddress().getCity().getName() ;
        return new TheaterDto(theater.getID(), theater.getName(), addresss, theater.getQuantityRoom(), theater.isStatus(),theater.getImage());

    }
}

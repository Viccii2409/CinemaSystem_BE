package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.dto.UserDto;
import com.springboot.CinemaSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT new com.springboot.CinemaSystem.dto.UserDto"+
            "(u.id,u.name,  "+
            "u.email,u.phone,u.address,u.dob,u.gender,u.points,u.level.name,u.status)"+
            "FROM User u Where u.user_type = :userType")
    List<UserDto> findByUserType(@Param("userType") String userType);
    Optional<User> findById(Long ID);
}

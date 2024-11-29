package com.springboot.CinemaSystem.repository;

import com.springboot.CinemaSystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByEmail(String email);
}

package com.example.springbatch.part4;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Collection;

public interface UserRepository extends JpaRepository<User , Long> {
    Collection<User> findAllByUpdatedDate(LocalDate now);

    @Query(value = "select min(id) from User")
    long findMinId();
    @Query(value = "select max(id) from User")
    long findMaxId();
}

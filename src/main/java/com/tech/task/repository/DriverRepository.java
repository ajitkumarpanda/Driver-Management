package com.tech.task.repository;

import com.tech.task.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {


    @Query("Select d from Driver d where d.creationDate >= :date")
    List<Driver> findByCreationDateAfter(@Param("date") Date date);
}

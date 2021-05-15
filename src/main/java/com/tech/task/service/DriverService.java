package com.tech.task.service;

import com.tech.task.entity.Driver;
import com.tech.task.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Service
public class DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverService.class);

    @Autowired
    private DriverRepository repository;

    public Driver saveDriver(Driver driver) {
        updateCreatedDate(driver);
        logger.info("ADD_DRIVER, driver{} requested add to database ", driver);
        driver = repository.save(driver);
        logger.info("ADD_DRIVER, driver{} added to database ", driver);
        return driver;
    }

    private void updateCreatedDate(Driver driver){
        LocalDate localDate = LocalDate.now();
        driver.setCreationDate(java.sql.Date.valueOf(localDate));
    }

    public List<Driver> getDrivers() {
        List<Driver> driverList = repository.findAll();
        logger.info("ALL_DRIVERS, all Drivers{} from database ", driverList);
        return driverList;
    }

    public List<Driver> getDriversByDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        List<Driver> driverList = null;
        try {
            driverList = repository.findByCreationDateAfter(formatter.parse(date));
        } catch (ParseException e) {
            logger.error("Parsing error for the provided date", e.getMessage());
            e.printStackTrace();
        }
        logger.info("ALL_DRIVERS, all Drivers{} created after date{} from database ", driverList, date);
        return driverList;
    }

}

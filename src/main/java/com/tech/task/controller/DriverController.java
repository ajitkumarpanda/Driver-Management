package com.tech.task.controller;

import com.tech.task.entity.Driver;
import com.tech.task.service.DriverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
public class DriverController {
    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private DriverService service;

    @PostMapping(value = "/driver/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public Driver addDriver(@RequestBody Driver driver) {
        logger.info("ADD_DRIVER, create driver{} requested ", driver);
        return service.saveDriver(driver);
    }

    @GetMapping(value = "/drivers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Driver> findAllDrivers() {
        logger.info("ALL_DRIVERS, all drivers requested ");
        return service.getDrivers();
    }

    @GetMapping(value = "/drivers/byDate", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Driver> findProductById(@QueryParam("date") String date) {
        logger.info("FIND_PRODUCT, products created after date{} requested ", date);
        return service.getDriversByDate(date);
    }
}

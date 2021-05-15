package com.tech.task.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.task.entity.Driver;
import com.tech.task.service.DriverService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void addDriver() throws Exception {
        when(driverService.saveDriver(any())).thenReturn(aDriver());

        mockMvc.perform(post("/driver/create")
                .content(mapper.writeValueAsString(aDriver()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(aDriver())));
    }

    @Test
    void findAllDrivers() throws Exception {
        when(driverService.getDrivers()).thenReturn(Collections.singletonList(aDriver()));

        mockMvc.perform(get("/drivers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(aDriver()))))
                .andExpect(status().isOk());
    }

    @Test
    void findProductById() throws Exception {
        String date = "1980-05-01";
        when(driverService.getDriversByDate(date)).thenReturn(Collections.singletonList(aDriver()));

        mockMvc.perform(get("/drivers/byDate")
                .queryParam("date", date)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(Collections.singletonList(aDriver()))))
                .andExpect(status().isOk());
    }

    private Driver aDriver() throws ParseException {
        Driver newDriver = new Driver();
        String sDate1="1980-05-01";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

        newDriver.setFirstName("John");
        newDriver.setLastName("Smith");
        newDriver.setBirthDate(date);
        newDriver.setCreationDate(date);
        return newDriver;
    }

}
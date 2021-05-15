package com.tech.task.service;

import com.tech.task.entity.Driver;
import com.tech.task.repository.DriverRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class DriverServiceTest {

    @MockBean
    private DriverRepository driverRepository;

    @Autowired
    private DriverService driverService;

    @Test
    void saveDriver() throws ParseException {
        Driver driver = aDriver();
        when(driverRepository.save(driver)).thenReturn(driver);
        assertEquals(driver.getFirstName(), driverService.saveDriver(driver).getFirstName());
    }

    @Test
    void getDrivers() {
        when(driverRepository.findAll()).thenReturn(aDriverList());
        assertEquals(2, driverService.getDrivers().size());
    }

    @Test
    void getDriversByDate() throws ParseException {
        String date = "1980-05-01";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = formatter.parse(date);
        when(driverRepository.findByCreationDateAfter(date1)).thenReturn(aDriverList());
        assertEquals(2, driverService.getDriversByDate(date).size());
    }

    private Driver aDriver() throws ParseException {
        Driver newDriver = new Driver();
        String sDate1="1980-05-01";
        Date date=new SimpleDateFormat("yyyy-MM-dd").parse(sDate1);

        newDriver.setFirstName("John");
        newDriver.setLastName("Smith");
        newDriver.setBirthDate(date);
        newDriver.setCreationDate(new Date());
        return newDriver;
    }

    private List<Driver> aDriverList(){
        return Stream.of(
                new Driver(1L, "John", "Smith", new Date(), new Date()),
                new Driver(2L, "Steve", "Jordan", new Date(), new Date()))
                .collect(Collectors.toList());
    }
}
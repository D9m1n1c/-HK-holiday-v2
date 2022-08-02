package com.example.demo.holidays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/holidaydata")
public class HolidayController {

    @Autowired
    private HolidayFetch holidayFetch;
    private final HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public void getHolidays() throws Exception {
        holidayFetch.holidayData();
    }

}

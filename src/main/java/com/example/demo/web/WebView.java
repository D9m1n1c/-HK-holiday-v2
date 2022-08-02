package com.example.demo.web;

import com.example.demo.holidays.HolidayFetch;
import com.example.demo.holidays.HolidayRepository;
import com.example.demo.holidays.Holidays;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@PageTitle("HK Holiday")
@Route(value = "")
public class WebView extends VerticalLayout{

    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private HolidayFetch holidayFetch;
//    List<Holidays> holidaysList = holidayRepository.findAll();
    Grid<Holidays> grid = new Grid<>(Holidays.class, false);

    public Grid<Holidays> getGrid() {
        grid.addColumn(Holidays::getUid).setHeader("UID");
        grid.addColumn(Holidays::getDtstart).setHeader("Start Date");
        grid.addColumn(Holidays::getDtend).setHeader("End Date");
        grid.addColumn(Holidays::getSummary).setHeader("Summary");

        return grid;
    }

    public WebView() {
        Button getData = new Button("Get Data");
            getData.addClickListener(event -> {
                try {
                    holidayFetch.holidayData();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

        DatePicker startDate = new DatePicker("Start Date");
            startDate.setValue(LocalDate.now());

        DatePicker endDate = new DatePicker("End Date");
            endDate.setValue(LocalDate.now().plusYears(1));


        ZoneId dfZoneId = ZoneId.systemDefault();

            Button search = new Button("Search");
            search.addClickListener(event -> {
                LocalDate startlocaldate = startDate.getValue();
                LocalDate endlocaldate = endDate.getValue();

                Date startDateDt = Date.from(startlocaldate.atStartOfDay(dfZoneId).toInstant());
                Date endDateDt = Date.from(endlocaldate.atStartOfDay(dfZoneId).toInstant());
                    grid.setItems(holidayRepository.findHolidaysByDate(startDateDt, endDateDt));
                    holidayRepository.findAll();
            });

        HorizontalLayout bar = new HorizontalLayout(getData, startDate, endDate, search);
        bar.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

        add(new H1("Dominic Chan"),
                bar,
                getGrid());
    }
}



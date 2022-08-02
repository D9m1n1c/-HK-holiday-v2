package com.example.demo.holidays;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;


@Repository
public interface HolidayRepository extends JpaRepository<Holidays,Long> {

    @Query("SELECT h FROM Holidays h WHERE (h.dtstart >= :startDate) AND (h.dtend <= :endDate)")
    List<Holidays> findHolidaysByDate(
            @Param("startDate")Date startDate,
            @Param("endDate")Date endDate
            );
}


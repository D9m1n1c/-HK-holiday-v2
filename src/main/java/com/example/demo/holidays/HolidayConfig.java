package com.example.demo.holidays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class HolidayConfig {

    @Bean
    CommandLineRunner commandLineRunner(HolidayRepository repository) {
        return args -> {
            Holidays holidays = new Holidays();

            repository.saveAll(
                    List.of(holidays)
            );
        };
    }
}

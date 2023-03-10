package com.amentity_reservation_system;

import com.amentity_reservation_system.model.AmenityType;
import com.amentity_reservation_system.model.Reservation;
import com.amentity_reservation_system.model.User;
import com.amentity_reservation_system.repos.ReservationRepository;
import com.amentity_reservation_system.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;


@SpringBootApplication
public class AmentityReservationSystemApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AmentityReservationSystemApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, ReservationRepository reservationRepository) {
        return (args) -> {
            User user = userRepository.save(new User());
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Reservation reservation = Reservation.builder()
                    .reservationDate(localDate)
                    .startTime(LocalTime.of(12, 00))
                    .endTime(LocalTime.of(13, 00))
                    .user(user)
                    .amentityType(AmenityType.POOL)
                    .build();
            reservationRepository.save(reservation);
        };
    }

}

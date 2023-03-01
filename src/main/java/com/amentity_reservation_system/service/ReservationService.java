package com.amentity_reservation_system.service;

import com.amentity_reservation_system.model.Reservation;
import com.amentity_reservation_system.repos.ReservationRepository;
import com.amentity_reservation_system.repos.UserRepository;
import com.amentity_reservation_system.util.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    public ReservationService(final ReservationRepository reservationRepository,
            final UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public List<Reservation> findAll() {
        final List<Reservation> reservations = reservationRepository.findAll(Sort.by("id"));
        return reservations.stream()
                .collect(Collectors.toList());
    }

    public Reservation get(final Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final Reservation reservation) {
        return reservationRepository.save(reservation).getId();
    }

    public void update(final Long id, final Reservation reservation) {
        final Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        reservationRepository.save(reservation);
    }

    public void delete(final Long id) {
        reservationRepository.deleteById(id);
    }


}

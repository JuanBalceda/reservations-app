package com.balceda.reservationsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balceda.reservationsapp.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String>{

}

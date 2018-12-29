package com.balceda.reservationsapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.balceda.reservationsapp.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

	@Query("Select r from Reservation r where r.dateIn=:in and r.dateOut=:out")
	public List<Reservation> find(@Param("in") Date dateIn, @Param("out") Date dateOut);

}

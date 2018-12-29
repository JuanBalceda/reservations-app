package com.balceda.reservationsapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balceda.reservationsapp.model.Reservation;
import com.balceda.reservationsapp.repository.ReservationRepository;

@Service
@Transactional(readOnly = true)
public class ReservationService {

	private final ReservationRepository reservationRepository;

	public ReservationService(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}

	@Transactional
	public Reservation create(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	@Transactional
	public Reservation update(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	@Transactional
	public void delete(Reservation reservation) {
		this.reservationRepository.delete(reservation);
	}

	public List<Reservation> findAll() {
		return this.reservationRepository.findAll();
	}

	public List<Reservation> find(Date in, Date out) {
		return this.reservationRepository.find(in, out);
	}

	public Reservation findByIdCode(String idCode) {
		return this.reservationRepository.findByIdCode(idCode);
	}
}

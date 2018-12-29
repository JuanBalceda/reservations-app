package com.balceda.reservationsapp.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balceda.reservationsapp.controller.vo.ReservationVO;
import com.balceda.reservationsapp.model.Reservation;
import com.balceda.reservationsapp.service.ReservationService;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping
	public ResponseEntity<Reservation> createReservation(@RequestBody ReservationVO reservationVO) {
		Reservation reservation = new Reservation();
		reservation.setDateIn(reservationVO.getDateIn());
		reservation.setDateOut(reservationVO.getDateOut());
		reservation.setIdCode(reservationVO.getIdCode());
		reservation.setPeopleAmount(reservationVO.getPeopleAmount());
		reservation.setDescription(reservationVO.getDescription());
		reservation.setStatus(reservationVO.getStatus());

		return new ResponseEntity<>(this.reservationService.create(reservation), HttpStatus.CREATED);
	}

	@PutMapping("/{idCode}")
	public ResponseEntity<Reservation> updateReservation(@PathVariable("idCode") String idCode,
			ReservationVO reservationVO) {

		Reservation reservation = this.reservationService.findByIdCode(idCode);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			reservation.setDateIn(reservationVO.getDateIn());
			reservation.setDateOut(reservationVO.getDateOut());
			reservation.setIdCode(reservationVO.getIdCode());
			reservation.setPeopleAmount(reservationVO.getPeopleAmount());
			reservation.setDescription(reservationVO.getDescription());
			reservation.setStatus(reservationVO.getStatus());

			return new ResponseEntity<>(this.reservationService.update(reservation), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{idCode}")
	public void removeReservation(@PathVariable("idCode") String idCode) {
		Reservation reservation = this.reservationService.findByIdCode(idCode);
		if (reservation != null) {
			this.reservationService.delete(reservation);
		}
	}

	@GetMapping
	public ResponseEntity<List<Reservation>> findAllReservations() {
		List<Reservation> reservations = this.reservationService.findAll();
		if (reservations != null) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{idCode}")
	public ResponseEntity<Reservation> findByIdNumberClient(@PathVariable("idCode") String idCode) {
		Reservation reservation = this.reservationService.findByIdCode(idCode);
		if (reservation != null) {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(reservation, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/in/{dateIn}/out/{dateOut}")
	public ResponseEntity<List<Reservation>> findByLastNameClient(@PathVariable("dateIn") Date dateIn,
			@PathVariable("dateOut") Date dateOut) {
		List<Reservation> reservations = this.reservationService.find(dateIn, dateOut);
		if (!reservations.isEmpty()) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

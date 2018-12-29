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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/reservation")
@Api(tags = "reservation")
public class ReservationController {

	private final ReservationService reservationService;

	public ReservationController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@PostMapping
	@ApiOperation(value = "Create a reservation", notes = "This service creates a new reservation")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Reservation created successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
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
	@ApiOperation(value = "Update a reservation", notes = "This service updates an existing reservation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reservation updated successfully"),
			@ApiResponse(code = 404, message = "Client not found") })
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
	@ApiOperation(value = "Delete a reservation", notes = "This service deletes an existing reservation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reservation deleted successfully"),
			@ApiResponse(code = 404, message = "Client not found") })
	public void removeReservation(@PathVariable("idCode") String idCode) {
		Reservation reservation = this.reservationService.findByIdCode(idCode);
		if (reservation != null) {
			this.reservationService.delete(reservation);
		}
	}

	@GetMapping
	@ApiOperation(value = "List all reservations", notes = "This service lists all existing reservations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reservations listed successfully"),
			@ApiResponse(code = 404, message = "Reservations not found") })
	public ResponseEntity<List<Reservation>> findAllReservations() {
		List<Reservation> reservations = this.reservationService.findAll();
		if (reservations != null) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{idCode}")
	@ApiOperation(value = "Find a reservation by id", notes = "This service gets an existing reservation by a given id_code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reservation found"),
			@ApiResponse(code = 404, message = "Reservation not found") })
	public ResponseEntity<Reservation> findByIdNumberClient(@PathVariable("idCode") String idCode) {
		Reservation reservation = this.reservationService.findByIdCode(idCode);
		if (reservation != null) {
			return new ResponseEntity<>(reservation, HttpStatus.OK);
		}
		return new ResponseEntity<>(reservation, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/in/{dateIn}/out/{dateOut}")
	@ApiOperation(value = "List all reservations between dates", notes = "This service lists all existing reservations between dates")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Reservations listed successfully"),
			@ApiResponse(code = 404, message = "Reservations not found") })
	public ResponseEntity<List<Reservation>> findByLastNameClient(@PathVariable("dateIn") Date dateIn,
			@PathVariable("dateOut") Date dateOut) {
		List<Reservation> reservations = this.reservationService.find(dateIn, dateOut);
		if (!reservations.isEmpty()) {
			return new ResponseEntity<>(reservations, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

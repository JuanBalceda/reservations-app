package com.balceda.reservationsapp.controller.vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationVO {

	private Date dateIn;
	private Date dateOut;
	private String idCode;
	private String peopleAmount;
	private String description;
	private String status;

	public ReservationVO() {

	}
}

package com.balceda.reservationsapp.controller.vo;

import lombok.Data;

@Data
public class ClientVO {

	private String idNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;

	public ClientVO() {
	}
}

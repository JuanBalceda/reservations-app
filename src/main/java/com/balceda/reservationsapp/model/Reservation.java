package com.balceda.reservationsapp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private Date dateIn;
	private Date dateOut;
	private String peopleAmount;
	private String description;
	private String status;

	@ManyToOne
	@JoinColumn(name = "id_client")
	private Client client;

	public Reservation() {

	}

}

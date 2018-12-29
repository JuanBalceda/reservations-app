package com.balceda.reservationsapp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "client")
@NamedQuery(name = "Client.findByIdNumber", query = "select c from Client c where c.idNumber = ?1")
public class Client {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	private String idNumber;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;

	@OneToMany(mappedBy = "client")
	private Set<Reservation> reservations;

	public Client() {
	}
}

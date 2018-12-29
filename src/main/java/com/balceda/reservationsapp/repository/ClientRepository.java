package com.balceda.reservationsapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balceda.reservationsapp.model.Client;

public interface ClientRepository extends JpaRepository<Client, String> {

	public List<Client> findByLastName(String lastName);

	public Client findByPhone(String phone);

	public Client findByEmail(String email);

	public Client findByIdNumber(String idNumber);
}

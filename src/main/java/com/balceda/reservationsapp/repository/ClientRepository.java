package com.balceda.reservationsapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.balceda.reservationsapp.model.Client;


public interface ClientRepository extends JpaRepository<Client, String>{
	
}

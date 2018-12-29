package com.balceda.reservationsapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balceda.reservationsapp.model.Client;
import com.balceda.reservationsapp.repository.ClientRepository;

@Service
@Transactional(readOnly = true)
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Transactional
	public Client create(Client client) {
		return this.clientRepository.save(client);
	}

	@Transactional
	public Client update(Client client) {
		return this.clientRepository.save(client);
	}

	@Transactional
	public void delete(Client client) {
		this.clientRepository.delete(client);
	}

	public List<Client> findByLastName(String lastName) {
		return this.clientRepository.findByLastName(lastName);
	}

	public Client findByPhone(String phone) {
		return this.clientRepository.findByPhone(phone);
	}

	public Client findByEmail(String email) {
		return this.clientRepository.findByEmail(email);
	}

	public Client findByIdNumber(String idNumber) {
		return this.clientRepository.findByIdNumber(idNumber);
	}

	public List<Client> findAll() {
		return this.clientRepository.findAll();
	}
}

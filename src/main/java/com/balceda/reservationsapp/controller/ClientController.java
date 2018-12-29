package com.balceda.reservationsapp.controller;

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

import com.balceda.reservationsapp.controller.vo.ClientVO;
import com.balceda.reservationsapp.model.Client;
import com.balceda.reservationsapp.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	public ResponseEntity<Client> createClient(@RequestBody ClientVO clientVO) {
		Client client = new Client();
		client.setIdNumber(clientVO.getIdNumber());
		client.setFirstName(clientVO.getFirstName());
		client.setLastName(clientVO.getLastName());
		client.setAddress(clientVO.getAddress());
		client.setPhone(clientVO.getPhone());
		client.setEmail(clientVO.getEmail());

		return new ResponseEntity<>(this.clientService.create(client), HttpStatus.CREATED);
	}

	@PutMapping("/{idNumber}")
	public ResponseEntity<Client> updateClient(@PathVariable("idNumber") String idNumber, ClientVO clientVO) {

		Client client = this.clientService.findByIdNumber(idNumber);
		if (client == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			client.setFirstName(clientVO.getFirstName());
			client.setLastName(clientVO.getLastName());
			client.setAddress(clientVO.getAddress());
			client.setPhone(clientVO.getPhone());
			client.setEmail(clientVO.getEmail());

			return new ResponseEntity<>(this.clientService.update(client), HttpStatus.OK);
		}
	}

	@DeleteMapping("/{idNumber}")
	public void removeClient(@PathVariable("idNumber") String idNumber) {
		Client client = this.clientService.findByIdNumber(idNumber);
		if (client != null) {
			this.clientService.delete(client);
		}
	}

	@GetMapping
	public ResponseEntity<List<Client>> findAllClients() {
		List<Client> clients = this.clientService.findAll();
		if (clients != null) {
			return new ResponseEntity<>(clients, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{idNumber}")
	public ResponseEntity<Client> findByIdNumberClient(@PathVariable("idNumber") String idNumber) {
		Client client = this.clientService.findByIdNumber(idNumber);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/lastname/{lastName}")
	public ResponseEntity<List<Client>> findByLastNameClient(@PathVariable("lastName") String lastName) {
		List<Client> clients = this.clientService.findByLastName(lastName);
		if (!clients.isEmpty()) {
			return new ResponseEntity<>(clients, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/phone/{phone}")
	public ResponseEntity<Client> findByPhoneClient(@PathVariable("phone") String phone) {
		Client client = this.clientService.findByPhone(phone);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Client> findByEmailClient(@PathVariable("email") String email) {
		Client client = this.clientService.findByEmail(email);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

}

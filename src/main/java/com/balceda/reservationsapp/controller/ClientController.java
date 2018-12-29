package com.balceda.reservationsapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		super();
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

	@PutMapping("/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") String idNumber, ClientVO clientVO) {

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
}

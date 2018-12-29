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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/client")
@Api(tags = "client")
public class ClientController {

	private final ClientService clientService;

	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@PostMapping
	@ApiOperation(value = "Create a client", notes = "This service creates a new client")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Client created successfully"),
			@ApiResponse(code = 400, message = "Invalid request") })
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
	@ApiOperation(value = "Update a client", notes = "This service updates an existing client")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client updated successfully"),
			@ApiResponse(code = 404, message = "Client not found") })
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
	@ApiOperation(value = "Delete a client", notes = "This service deletes an existing client")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client deleted successfully"),
			@ApiResponse(code = 404, message = "Client not found") })
	public void removeClient(@PathVariable("idNumber") String idNumber) {
		Client client = this.clientService.findByIdNumber(idNumber);
		if (client != null) {
			this.clientService.delete(client);
		}
	}

	@GetMapping
	@ApiOperation(value = "List all clients", notes = "This service lists all existing clients")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Clients listed successfully"),
			@ApiResponse(code = 404, message = "Clients not found") })
	public ResponseEntity<List<Client>> findAllClients() {
		List<Client> clients = this.clientService.findAll();
		if (clients != null) {
			return new ResponseEntity<>(clients, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{idNumber}")
	@ApiOperation(value = "Find a client by id", notes = "This service gets an existing client by a given id_number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client found"),
			@ApiResponse(code = 404, message = "Client not found") })
	public ResponseEntity<Client> findByIdNumberClient(@PathVariable("idNumber") String idNumber) {
		Client client = this.clientService.findByIdNumber(idNumber);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/lastname/{lastName}")
	@ApiOperation(value = "Find a client by lastName", notes = "This service gets an existing client by a given lastName")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client found"),
			@ApiResponse(code = 404, message = "Client not found") })
	public ResponseEntity<List<Client>> findByLastNameClient(@PathVariable("lastName") String lastName) {
		List<Client> clients = this.clientService.findByLastName(lastName);
		if (!clients.isEmpty()) {
			return new ResponseEntity<>(clients, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/phone/{phone}")
	@ApiOperation(value = "Find a client by phone", notes = "This service gets an existing client by a given phone")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client found"),
			@ApiResponse(code = 404, message = "Client not found") })
	public ResponseEntity<Client> findByPhoneClient(@PathVariable("phone") String phone) {
		Client client = this.clientService.findByPhone(phone);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/email/{email}")
	@ApiOperation(value = "Find a client by email", notes = "This service gets an existing client by a given email")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Client found"),
			@ApiResponse(code = 404, message = "Client not found") })
	public ResponseEntity<Client> findByEmailClient(@PathVariable("email") String email) {
		Client client = this.clientService.findByEmail(email);
		if (client != null) {
			return new ResponseEntity<>(client, HttpStatus.OK);
		}
		return new ResponseEntity<>(client, HttpStatus.NOT_FOUND);
	}

}

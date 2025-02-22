package com.agcapital.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agcapital.domain.Cliente;
import com.agcapital.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

	@GetMapping
	public  ResponseEntity<Page<Cliente>> getAllClientes(@RequestParam("page") int page, @RequestParam("size") int size){
		return ResponseEntity.ok(clienteService.getAllClientes(page,size));
	}

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") UUID id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    	Cliente novoCliente = clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable("id") UUID id, @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.updateCliente(id, cliente);
        return clienteAtualizado != null ? ResponseEntity.ok(clienteAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable("id") UUID id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
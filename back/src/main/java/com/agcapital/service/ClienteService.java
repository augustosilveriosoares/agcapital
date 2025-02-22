package com.agcapital.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.agcapital.domain.Cliente;
import com.agcapital.domain.Status;
import com.agcapital.exception.ClienteNotFoundException;
import com.agcapital.exception.ClienteWithProjectsOpenException;
import com.agcapital.exception.EmailDuplicateException;
import com.agcapital.repository.ClienteRepository;


@Service
@CrossOrigin(origins = "http://localhost")
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente>getAllClientes(int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
	    Page<Cliente> clientes = clienteRepository.findAll(pageable);
	    return clientes;
    }

    public Cliente getClienteById(UUID id) {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException());
    }

    public Cliente createCliente(Cliente cliente) {
    	 if (clienteRepository.existsByEmail(cliente.getEmail())) 
             throw new EmailDuplicateException();
             
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(UUID id, Cliente cliente) {
        Cliente clienteOld = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException());
        clienteOld.setNome(cliente.getNome());
        clienteOld.setEmail(cliente.getEmail());
        return clienteRepository.save(clienteOld);
        
    }

    public void deleteCliente(UUID id) {
    	Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException());
        boolean possuiProjetosEmAndamento = cliente.getProjetos().stream()
                .anyMatch(projeto -> projeto.getStatus() != Status.FINALIZADO);

        if (possuiProjetosEmAndamento) 
            throw new ClienteWithProjectsOpenException();
        
        clienteRepository.delete(cliente);
		
		
    }
}

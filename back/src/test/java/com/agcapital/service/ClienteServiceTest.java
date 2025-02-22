package com.agcapital.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.agcapital.domain.Cliente;
import com.agcapital.domain.Projeto;
import com.agcapital.domain.Status;
import com.agcapital.exception.ClienteWithProjectsOpenException;
import com.agcapital.repository.ClienteRepository;

public class ClienteServiceTest {
	
	 @Mock
	    private ClienteRepository clienteRepository;

	    @InjectMocks
	    private ClienteService clienteService;

	    private Cliente cliente;
	    private UUID clienteId;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	        clienteId = UUID.randomUUID();
	        cliente = new Cliente(clienteId, "Cliente Teste", "cliente@teste.com", null);
	    }

	    @Test
	    public void testGetAllClientes() {
	        Page<Cliente> projetosPage = new PageImpl<>(List.of(cliente));
	        when(clienteRepository.findAll(Mockito.any(Pageable.class))).thenReturn(projetosPage);
	        Page<Cliente> result = clienteService.getAllClientes(0, 10);
	        
	        assertNotNull(result);
	        assertEquals(1, result.getTotalElements());
	    }

	    @Test
	    public void testGetClienteById() {
	        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
	        Cliente result = clienteService.getClienteById(clienteId);
	        assertNotNull(result);
	        assertEquals(clienteId, result.getId());
	    }

	    @Test
	    public void testCreateCliente() {
	        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

	        Cliente novoCliente = clienteService.createCliente(cliente);

	        assertNotNull(novoCliente);
	        assertEquals(cliente.getNome(), novoCliente.getNome());
	    }

	    @Test
	    public void testUpdateCliente() {
	    	 when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
	    	    when(clienteRepository.save(Mockito.any(Cliente.class)))
	            .thenAnswer(invocation -> invocation.getArgument(0)); 

	    	    Cliente updatedCliente = cliente;
	    	    updatedCliente.setNome("Novo Nome");
	    	    
	    	    Cliente result = clienteService.updateCliente(clienteId, updatedCliente);

	    	    assertNotNull(result);
	    	    assertEquals("Novo Nome", result.getNome());
	    	    
	    }

	    @Test
	    public void testDeleteCliente_ComProjetosEmAndamento() {
	   
	        Projeto projetoEmAndamento = new Projeto(UUID.randomUUID(), "Projeto A", Status.EM_ANDAMENTO, cliente,null);
	        cliente.setProjetos(List.of(projetoEmAndamento));

	        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));

            assertThrows(ClienteWithProjectsOpenException.class, () -> clienteService.deleteCliente(clienteId));
	        verify(clienteRepository, times(0)).delete(cliente);
	    }
}

package com.agcapital.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.agcapital.domain.Atividade;
import com.agcapital.domain.Cliente;
import com.agcapital.domain.Projeto;
import com.agcapital.domain.Status;
import com.agcapital.exception.ProjetoWithActivityException;
import com.agcapital.repository.ProjetoRepository;

public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    private Projeto projeto;
    private UUID projetoId;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projetoId = UUID.randomUUID();
        Cliente cliente = new Cliente(UUID.randomUUID(), "Cliente Teste", "cliente@teste.com", null);     
        Atividade atividade1 = new Atividade(UUID.randomUUID(), "Atividade 1", null, Status.EM_ANDAMENTO);
        Atividade atividade2 = new Atividade(UUID.randomUUID(), "Atividade 2", null, Status.AGUARDANDO);
        List<Atividade> atividades = List.of(atividade1, atividade2);
        this.projeto = new Projeto(projetoId, "Projeto Teste", Status.EM_ANDAMENTO, cliente, atividades);
        
    }

    @Test
    public void testGetAllProjetos() {
        Page<Projeto> projetosPage = new PageImpl<>(List.of(projeto));
        when(projetoRepository.findAll(Mockito.any(Pageable.class))).thenReturn(projetosPage);
        Page<Projeto> result = projetoService.getAllProjetos(0, 10);
        
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
    }

    @Test
    public void testGetProjetoById() {
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));

        Optional<Projeto> result = projetoService.getProjetoById(projetoId);

        assertTrue(result.isPresent());
        assertEquals(projetoId, result.get().getId());
    }

    @Test
    public void testCreateProjeto() {
        when(projetoRepository.save(Mockito.any(Projeto.class))).thenReturn(projeto);

        Projeto result = projetoService.createProjeto(projeto);

        assertNotNull(result);
        assertEquals("Projeto Teste", result.getNome());
    }

    @Test
    public void testUpdateProjeto() {
    	
    	    when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));
    	    when(projetoRepository.save(Mockito.any(Projeto.class)))
            .thenAnswer(invocation -> invocation.getArgument(0)); 

    	    Projeto updatedProjeto = projeto;
    	    updatedProjeto.setNome("Nova descricao do projeto");
    	    updatedProjeto.setStatus(Status.FINALIZADO);
    	    Projeto result = projetoService.updateProjeto(projetoId, updatedProjeto);

    	    assertNotNull(result);
    	    assertEquals("Nova descricao do projeto", result.getNome());
    	    assertEquals(Status.FINALIZADO, result.getStatus());
    }

    @Test
    public void testDeleteProjetoComAtividadesEmAndamento() {
    	Cliente cliente = new Cliente(UUID.randomUUID(), "Cliente Teste", "cliente@teste.com", null);    
        Atividade atividade1 = new Atividade(UUID.randomUUID(), "Atividade 1", null, Status.EM_ANDAMENTO);
        Atividade atividade2 = new Atividade(UUID.randomUUID(), "Atividade 2", null, Status.EM_ANDAMENTO);
        List<Atividade> atividades = List.of(atividade1, atividade2);
        Projeto projeto = new Projeto(UUID.randomUUID(), "Projeto Teste", Status.EM_ANDAMENTO, cliente, atividades);

  
        when(projetoRepository.findById(projetoId)).thenReturn(Optional.of(projeto));
        assertThrows(ProjetoWithActivityException.class, () -> projetoService.deleteProjeto(projetoId));
        verify(projetoRepository, times(0)).delete(projeto);
    }
}

package com.agcapital.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.agcapital.domain.Projeto;
import com.agcapital.domain.Status;
import com.agcapital.exception.ProjetoNotFoundException;
import com.agcapital.exception.ProjetoWithActivityException;
import com.agcapital.repository.ProjetoRepository;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public Page<Projeto> getAllProjetos(int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
	    Page<Projeto> projetos = projetoRepository.findAll(pageable);
	    return projetos;
    }

    public Optional<Projeto> getProjetoById(UUID id) {
        return projetoRepository.findById(id);
    }

    public Projeto createProjeto(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto updateProjeto(UUID id, Projeto projeto) {
        Projeto projetoOld = projetoRepository.findById(id).orElseThrow(() -> new ProjetoNotFoundException());
        projetoOld = projeto;
    	return projetoRepository.save(projetoOld);
        
        
    }

    public void deleteProjeto(UUID id) {
    	Projeto projeto = projetoRepository.findById(id).orElseThrow(() -> new ProjetoNotFoundException());
    	boolean possuiTarefasEmAndamento = projeto.getAtividades().stream().anyMatch(atividade -> atividade.getStatus() != Status.FINALIZADO);
    	if(possuiTarefasEmAndamento)
    		throw new ProjetoWithActivityException();
    	
    	projetoRepository.delete(projeto);
    	
        
    }
}
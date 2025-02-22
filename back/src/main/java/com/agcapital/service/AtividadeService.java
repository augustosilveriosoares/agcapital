package com.agcapital.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.agcapital.domain.Atividade;
import com.agcapital.domain.Status;
import com.agcapital.exception.AtividadeNotFoundException;
import com.agcapital.repository.AtividadeRepository;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    public Page<Atividade> getAllAtividades(int page, int size) {
    	Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
	    Page<Atividade> projetos = atividadeRepository.findAll(pageable);
	    return projetos;
    }

    public Atividade getAtividadeById(UUID id) {
        return atividadeRepository.findById(id).orElseThrow(() -> new AtividadeNotFoundException());
    }

    public Atividade createAtividade(Atividade atividade) {
        return atividadeRepository.save(atividade);
    }

    public Atividade updateAtividade(UUID id, Atividade atividade) {
    	Atividade atividadeOld = atividadeRepository.findById(id).orElseThrow(() -> new AtividadeNotFoundException());
        atividadeOld = atividade;
    	return atividadeRepository.save(atividadeOld);
    }

    public void deleteAtividade(UUID id) {
    	Atividade atividade = atividadeRepository.findById(id).orElseThrow(() -> new AtividadeNotFoundException());
    	atividade.setStatus(Status.CANCELADO);   	
    	atividadeRepository.delete(atividade);
    }
}

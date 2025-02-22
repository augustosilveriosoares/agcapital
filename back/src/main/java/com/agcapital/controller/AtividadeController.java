package com.agcapital.controller;

import java.util.Optional;
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

import com.agcapital.domain.Atividade;
import com.agcapital.service.AtividadeService;

@RestController
@RequestMapping("/api/atividades")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

	@GetMapping
	public  ResponseEntity<Page<Atividade>> getAllAtividades(@RequestParam("page") int page, @RequestParam("size") int size){
		return ResponseEntity.ok(atividadeService.getAllAtividades(page,size));
	}


    @GetMapping("/{id}")
    public ResponseEntity<Atividade> getAtividadeById(@PathVariable("id") UUID id) {
        Atividade atividade = atividadeService.getAtividadeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(atividade);
        
    }

    @PostMapping
    public ResponseEntity<Atividade> createAtividade(@RequestBody Atividade atividade) {
        Atividade atividadeSalva = atividadeService.createAtividade(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(atividadeSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Atividade> updateAtividade(@PathVariable("id") UUID id, @RequestBody Atividade atividade) {
        Atividade atividadeAtualizada = atividadeService.updateAtividade(id, atividade);
        return atividadeAtualizada != null ? ResponseEntity.ok(atividadeAtualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAtividade(@PathVariable("id") UUID id) {
    	atividadeService.deleteAtividade(id);
        return ResponseEntity.noContent().build();
    }
}
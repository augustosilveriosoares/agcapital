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

import com.agcapital.domain.Projeto;
import com.agcapital.service.ProjetoService;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

	@GetMapping
	public  ResponseEntity<Page<Projeto>> getAllProjetos(@RequestParam("page") int page, @RequestParam("size") int size){
		return ResponseEntity.ok(projetoService.getAllProjetos(page,size));
	}

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable("id") UUID id) {
        Optional<Projeto> projeto = projetoService.getProjetoById(id);
        return projeto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        Projeto projetoSalvo = projetoService.createProjeto(projeto);
        return ResponseEntity.status(HttpStatus.CREATED).body(projetoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable UUID id, @RequestBody Projeto projeto) {
        Projeto projetoAtualizado = projetoService.updateProjeto(id, projeto);
        return projetoAtualizado != null ? ResponseEntity.ok(projetoAtualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable UUID id) {
        projetoService.deleteProjeto(id);
        return ResponseEntity.noContent().build();
        		
    }
}
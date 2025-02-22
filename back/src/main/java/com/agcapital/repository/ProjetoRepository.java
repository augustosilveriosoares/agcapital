package com.agcapital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agcapital.domain.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

}

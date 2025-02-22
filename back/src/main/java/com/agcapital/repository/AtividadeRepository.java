package com.agcapital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agcapital.domain.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, UUID> {

}

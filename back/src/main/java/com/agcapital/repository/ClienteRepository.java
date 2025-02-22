package com.agcapital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agcapital.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
	
	 boolean existsByEmail(String email);

}

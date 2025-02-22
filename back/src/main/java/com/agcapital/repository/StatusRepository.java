package com.agcapital.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agcapital.domain.Status;

public interface StatusRepository  extends JpaRepository<Status, UUID>{

}

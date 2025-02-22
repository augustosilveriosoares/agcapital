package com.agcapital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agcapital.domain.Status;
import com.agcapital.repository.StatusRepository;

@Service
public class StatusService {
	
	@Autowired
	private StatusRepository statusRepository;
	
	public List<Status> findAll(){
		return statusRepository.findAll();
		
	}
}

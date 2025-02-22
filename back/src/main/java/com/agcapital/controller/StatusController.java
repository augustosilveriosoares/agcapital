package com.agcapital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agcapital.domain.Status;
import com.agcapital.service.StatusService;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;
    
    @GetMapping
    public ResponseEntity<List<Status>> getAllStatus(){
    	return ResponseEntity.status(HttpStatus.OK).body(statusService.findAll());
    }


}
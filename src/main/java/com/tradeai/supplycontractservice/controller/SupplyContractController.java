package com.tradeai.supplycontractservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.service.SupplyContractService;




@RestController
@RequestMapping ("/contract")
public class SupplyContractController {
	

	@Autowired
	private SupplyContractService service;
	
	
	@GetMapping (path ="/{contractId}", produces = MediaType.APPLICATION_JSON_VALUE , 
			consumes = MediaType.APPLICATION_JSON_VALUE  )
	public ResponseEntity<SupplyContractDTO> getSupplier(@PathVariable String contractId){
		
		SupplyContractDTO response = service.getContractByContractId(Integer.parseInt(contractId));
		
		return new ResponseEntity<SupplyContractDTO>(response, HttpStatus.OK);
		
	}

}

package com.tradeai.supplycontractservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;
import com.tradeai.supplycontractservice.response.SupplyContractResponse;
import com.tradeai.supplycontractservice.service.SupplyContractService;

@RestController
@RequestMapping("/activity")
public class SupplyContractActivityController {
	
	@Autowired
	private SupplyContractService service;
	
	public ResponseEntity<SupplyContractActivityDTO> getActivityByContractIdAndActivityId () {
		
		return null;
		
	}
	
	@PutMapping(path = "/{activityType}/status/{activityStatus}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplyContractResponse> addPendingActivity(@RequestBody SupplyContractRequest contract, @PathVariable String activityType, @PathVariable String activityStatus) {

		System.out.println("activityType : " + activityType);

		System.out.println("activityStatus : " + activityStatus);
		
		System.out.println("contract : " + contract);
		
		SupplyContractDTO dtoresponse = service.processContractActivity(contract, activityStatus);
		
		SupplyContractResponse response = SupplyContractController.mapContractDTOToResponse(dtoresponse);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}

}

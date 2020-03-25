package com.tradeai.supplycontractservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityStatusDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;
import com.tradeai.supplycontractservice.response.SupplyContractActivityResponse;
import com.tradeai.supplycontractservice.response.SupplyContractActivityStatusResponse;
import com.tradeai.supplycontractservice.response.SupplyContractResponse;
import com.tradeai.supplycontractservice.service.SupplyContractService;

@RestController
@RequestMapping("/contract")
public class SupplyContractController {

	@Autowired
	private SupplyContractService service;

	@GetMapping(path = "/{contractId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplyContractResponse> getContract(@PathVariable String contractId) {

		SupplyContractDTO dtoresponse = service.getContractByContractId(Integer.parseInt(contractId));
		
		SupplyContractResponse response = mapContractDTOToResponse(dtoresponse);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}
	
	@PutMapping(path = "/{contractId}/activity/{activityType}/status/S", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplyContractResponse> getContractForSettlement(@PathVariable String contractId,
			
			@PathVariable String activityType) {

		SupplyContractDTO dtoresponse = service.getContractByContractId(Integer.parseInt(contractId));
		dtoresponse.setContractStatus("S");
		
		
		
		dtoresponse = service.processContractActivity(dtoresponse);
		
		SupplyContractResponse response = mapContractDTOToResponse(dtoresponse);

		//return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}
	

	@PostMapping
	public ResponseEntity<SupplyContractResponse> saveNewPendingContract(@RequestBody SupplyContractRequest contract) {

		
		SupplyContractDTO dto = mapContractRequest (contract);
		
		
		
		SupplyContractDTO dtoresponse = service.processContractActivity(dto);
		
		SupplyContractResponse response = mapContractDTOToResponse(dtoresponse);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}
	
	
	
	
	

	private SupplyContractResponse mapContractDTOToResponse(SupplyContractDTO dto) {
		
		SupplyContractResponse response =new SupplyContractResponse();
		
		List<SupplyContractActivityDTO> activities = dto.getActivities();
		
		List<SupplyContractActivityResponse> activitiesRes = new ArrayList<>();
		
		List<SupplyContractActivityStatusResponse> statusRes = new ArrayList<>();
		
		activities.forEach(element -> {
			
			SupplyContractActivityResponse activityResponseElement =new SupplyContractActivityResponse();
			activityResponseElement.setContractId(element.getContractId());
			activityResponseElement.setSupplyContractActivityId(element.getSupplyContractActivityId());
			activityResponseElement.setActivityPrice(element.getActivityPrice());
			activityResponseElement.setActivityQuantity(element.getActivityQuantity());
			activityResponseElement.setActivityRate(element.getActivityRate());
			activityResponseElement.setActivityType(element.getActivityType());
			activityResponseElement.setActivityState(element.getActivityState());	
			
			List<SupplyContractActivityStatusDTO>  statusdto = element.getStatuses();
			
			statusdto.forEach(status -> {
				
				SupplyContractActivityStatusResponse statusActivityRes = new SupplyContractActivityStatusResponse();
				statusActivityRes.setContractActivityId(status.getContractActivityId());
				statusActivityRes.setContractId(status.getContractId());
				statusActivityRes.setActivityType(status.getActivityType());
				statusActivityRes.setActivityState(status.getActivityState());
				
				statusRes.add(statusActivityRes);
				
			});
			
			activityResponseElement.setStatuses(statusRes);
			
			
			activitiesRes.add(activityResponseElement);
			
			
			
		});
		
		
			response.setSupplyContractId(dto.getSupplyContractId());
			response.setSecurityCode(dto.getSecurityCode());
			response.setContractStatus(dto.getContractStatus());
			response.setCurrentQuantity(dto.getOriginalQuantity());
			response.setCurrentPrice(dto.getCurrentPrice());
			response.setCurrentRate(dto.getCurrentRate());
			response.setOriginalPrice(dto.getOriginalPrice());
			response.setOriginalQuantity(dto.getOriginalQuantity());
			response.setOriginalRate(dto.getOriginalRate());
			response.setActivityType(dto.getActivityType());
			
			response.setActivities(activitiesRes);
		
		return response;
	}

	private SupplyContractDTO mapContractRequest(SupplyContractRequest contract) {
		
		SupplyContractDTO dto = new SupplyContractDTO();
		
		
		
		
		dto.setSupplyContractId(contract.getSupplyContractId());
		dto.setSecurityCode(contract.getSecurityCode());
		
		dto.setCurrentPrice(contract.getCurrentPrice());
		dto.setOriginalPrice(contract.getOriginalPrice());
		
		dto.setCurrentQuantity(contract.getCurrentQuantity());
		dto.setOriginalQuantity(contract.getOriginalQuantity());
		
		dto.setOriginalRate(contract.getOriginalRate());
		dto.setCurrentRate(contract.getCurrentRate());
		
		dto.setActivityType(contract.getActivityType());
		
		dto.setContractStatus(contract.getContractStatus());
		
		dto.setSuppliedId(contract.getSuppliedId());
		
		
		
		
		/*List<SupplyContractActivityDTO> listOfActivities = new ArrayList<>();
		
		contract.getActivities().forEach(element -> {
			
			SupplyContractActivityDTO activityDto = new SupplyContractActivityDTO();
			activityDto.setContractId(element.getContractId());
			activityDto.setSupplyContractActivityId(element.getSupplyContractActivityId());
			activityDto.setActivityPrice(element.getActivityPrice());
			activityDto.setActivityQuantity(element.getActivityQuantity());
			activityDto.setActivityRate(element.getActivityRate());
			activityDto.setActivityType(element.getActivityType());
			activityDto.setActivityState(element.getActivityState());
			listOfActivities.add(activityDto);
			
			
			
		});
		
		
		
			
		
		dto.setActivities(listOfActivities);
		
		*/
		
		
		
		
		
		return dto;
	}

}

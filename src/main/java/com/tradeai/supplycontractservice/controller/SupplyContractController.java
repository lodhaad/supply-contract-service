package com.tradeai.supplycontractservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(path = "/hello")
	public String sayHello() {

		return "Hello Contract Service";

	}

	@GetMapping(path = "/{contractId}")
	public ResponseEntity<SupplyContractResponse> getContract(@PathVariable String contractId) {

		SupplyContractDTO dtoresponse = service.getContractByContractId(Integer.parseInt(contractId));

		SupplyContractResponse response = mapContractDTOToResponse(dtoresponse);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}

	/**
	 * 
	 * @param contract
	 * @return
	 */
	@PostMapping(path = "/addContract", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SupplyContractResponse> addContract(@RequestBody SupplyContractRequest contractRequest) {
		
		// add contract
		// add new pending activity.
		
		SupplyContractDTO dtoresponse = service.processContractActivity(contractRequest, "P");

		SupplyContractResponse response = mapContractDTOToResponse(dtoresponse);

		return new ResponseEntity<SupplyContractResponse>(response, HttpStatus.OK);

	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public static SupplyContractResponse mapContractDTOToResponse(SupplyContractDTO dto) {

		SupplyContractResponse response = new SupplyContractResponse();

		List<SupplyContractActivityDTO> activities = dto.getActivities();

		List<SupplyContractActivityResponse> activitiesRes = new ArrayList<>();

		List<SupplyContractActivityStatusResponse> statusRes = new ArrayList<>();

		activities.forEach(element -> {

			SupplyContractActivityResponse activityResponseElement = new SupplyContractActivityResponse();
			activityResponseElement.setContractId(element.getContractId());
			activityResponseElement.setSupplyContractActivityId(element.getSupplyContractActivityId());
			activityResponseElement.setActivityPrice(element.getActivityPrice());
			activityResponseElement.setActivityQuantity(element.getActivityQuantity());
			activityResponseElement.setActivityRate(element.getActivityRate());
			activityResponseElement.setActivityType(element.getActivityType());
			activityResponseElement.setActivityState(element.getActivityState());

			List<SupplyContractActivityStatusDTO> statusdto = element.getStatuses();

			statusdto.forEach(status -> {

				SupplyContractActivityStatusResponse statusActivityRes = new SupplyContractActivityStatusResponse();
				statusActivityRes.setContractActivityId(status.getContractActivityId());
				statusActivityRes.setContractId(status.getContractId());
				statusActivityRes.setActivityType(status.getActivityType());
				statusActivityRes.setActivityState(status.getActivityState());
				statusActivityRes.setContractActivityStatusId(status.getContractActivityStatusId());

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
		response.setSuppliedId(dto.getSuppliedId());

		response.setActivities(activitiesRes);

		return response;
	}
	
}

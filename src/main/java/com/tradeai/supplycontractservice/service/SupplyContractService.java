package com.tradeai.supplycontractservice.service;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;

public interface SupplyContractService {

	public SupplyContractDTO getContractByContractId(Integer contractId);
	
	public SupplyContractDTO processContractActivity(SupplyContractRequest contractRequest, String activityStatus);
	
	/**
	 * 
	 * @param contract
	 * @return
	 */
	default SupplyContractDTO mapContractRequest(SupplyContractRequest contract) {

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

		return dto;
	}


}

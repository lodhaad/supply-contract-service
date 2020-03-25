package com.tradeai.supplycontractservice.service;







import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;
import com.tradeai.supplycontractservice.response.SupplyContractResponse;



public interface SupplyContractService {
	

	public SupplyContractDTO getContractByContractId(Integer contractId);

	public SupplyContractDTO processContractActivity(SupplyContractDTO contract);
	
	
	
	



}

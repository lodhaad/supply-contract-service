package com.tradeai.supplycontractservice.service;



import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;



public interface SupplyContractService {
	

	public SupplyContractDTO getContractByContractId(Integer contractId);
	
	public SupplyContractActivityDTO 
	getActivityByActivityIdAndContractId(Integer contractId, Integer contractActivityId);



}

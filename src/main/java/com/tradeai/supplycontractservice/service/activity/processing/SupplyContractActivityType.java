package com.tradeai.supplycontractservice.service.activity.processing;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

public interface SupplyContractActivityType {
	
	public SupplyContractDTO processActivityAndStatus(SupplyContractDTO dto);

	

}

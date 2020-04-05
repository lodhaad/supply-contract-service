package com.tradeai.supplycontractservice.service.activity.processing;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

public interface SupplyContractActivityType {
	
	/**
	 * 
	 * @param contractRequest
	 * @return
	 */
	public SupplyContractDTO processActivityAndStatus(SupplyContractDTO supplyContractDTO, String activityStatus);

}

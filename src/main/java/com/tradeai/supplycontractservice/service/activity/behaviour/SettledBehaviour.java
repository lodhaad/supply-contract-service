package com.tradeai.supplycontractservice.service.activity.behaviour;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

public interface SettledBehaviour {
	
	public SupplyContractDTO onSettledBehaviour(SupplyContractDTO dto);

}

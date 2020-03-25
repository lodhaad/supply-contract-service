package com.tradeai.supplycontractservice.service.activity.behaviour;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

public interface PendingBehaviour {
	
	public SupplyContractDTO onPendingAction(SupplyContractDTO dao);

}

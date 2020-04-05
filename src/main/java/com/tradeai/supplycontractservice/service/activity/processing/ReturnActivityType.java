package com.tradeai.supplycontractservice.service.activity.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.service.activity.behaviour.PendingBehaviour;
import com.tradeai.supplycontractservice.service.activity.behaviour.SettledBehaviour;

@Component("ReturnActivity")
public class ReturnActivityType implements SupplyContractActivityType {

	@Autowired
	@Qualifier("ReturnPendingActivityBehaviour")
	private PendingBehaviour pendingActivity;

	@Autowired
	@Qualifier("ReturnSettledActivityBehaviour")
	private SettledBehaviour settledActivity;

	@Override
	public SupplyContractDTO processActivityAndStatus(SupplyContractDTO dto, String activityStatus) {
		if (activityStatus.equals("P")) {
			return pendingActivity.onPendingAction(dto);
		} else if (activityStatus.equals("S")) {
			return settledActivity.onSettledBehaviour(dto);
		} else {

			throw new RuntimeException("This activity type is not configured ");

		}

	}

}

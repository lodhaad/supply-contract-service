package com.tradeai.supplycontractservice.service.activity.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.service.activity.behaviour.PendingBehaviour;
import com.tradeai.supplycontractservice.service.activity.behaviour.SettledBehaviour;

@Component("NewActivity")
public class NewActivityType implements SupplyContractActivityType {

	@Autowired
	@Qualifier("NewPendingActivityBehaviour")
	private PendingBehaviour pendingActivity;

	@Autowired
	@Qualifier("NewSettledActivityBehaviour")
	private SettledBehaviour settledActivity;

	@Override
	public SupplyContractDTO processActivityAndStatus(SupplyContractDTO dto, String activityStatus) {

		if ("P".equals(activityStatus)) {
			return pendingActivity.onPendingAction(dto);
		} else if ("S".equals(activityStatus)) {
			return settledActivity.onSettledBehaviour(dto);
		} else {
			throw new RuntimeException("This activity type is not configured ");
		}

	}

}

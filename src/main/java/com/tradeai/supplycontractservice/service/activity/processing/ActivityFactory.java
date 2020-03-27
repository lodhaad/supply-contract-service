package com.tradeai.supplycontractservice.service.activity.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.service.activity.behaviour.NewPendingActivityBehaviour;
import com.tradeai.supplycontractservice.service.activity.behaviour.NewSettledActivityBehaviour;


@Component ("ActivityFactory")
public class ActivityFactory {
	
	@Autowired
	SupplyContractActivityType activity;
	
	@Autowired
	@Qualifier ("NewActivity")
	NewActivityType newActivity;
	
	public SupplyContractActivityType getActivityType(SupplyContractDTO dto) {
		
		if (dto.getActivityType().equals("N")) {
			
			
			  activity = newActivity ;
			  return activity;
			
		}
		
		return null;
	}

}

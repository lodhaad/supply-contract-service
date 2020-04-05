package com.tradeai.supplycontractservice.service.activity.processing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.constants.ActivityType;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;


@Component ("ActivityFactory")
public class ActivityFactory {
	
	@Autowired
	@Qualifier ("NewActivity")
	NewActivityType newActivity;
	
	@Autowired
	@Qualifier ("MarkActivity")
	MarkActivityType markActivity;
	
	@Autowired
	@Qualifier ("ReturnActivity")
	ReturnActivityType returnActivity;
	
	/**
	 * 
	 * @param dto
	 * @return
	 */
	public SupplyContractActivityType getActivityType(SupplyContractRequest contractRequest) {
		SupplyContractActivityType activity = null;
		
		if (ActivityType.N.name().equals(contractRequest.getActivityType())) {
			activity = newActivity ;
		} else if(ActivityType.M.name().equals(contractRequest.getActivityType())) {
			activity = markActivity;
		} else if(ActivityType.R.name().equals(contractRequest.getActivityType()) || ActivityType.P.name().equals(contractRequest.getActivityType())) {
			activity = returnActivity;
		} else {
			throw new RuntimeException("Not a supported activity Type");
		}
		
		return activity;
	}

}

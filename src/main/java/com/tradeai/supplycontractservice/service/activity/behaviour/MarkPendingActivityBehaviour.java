package com.tradeai.supplycontractservice.service.activity.behaviour;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.constants.ActivityType;
import com.tradeai.supplycontractservice.data.SupplyActivityRepository;
import com.tradeai.supplycontractservice.data.SupplyActivityStatusRepository;
import com.tradeai.supplycontractservice.data.SupplyContractRepository;
import com.tradeai.supplycontractservice.datamodel.SupplyActivity;
import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;
import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityStatusDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

@Component("MarkPendingActivityBehaviour")
public class MarkPendingActivityBehaviour implements PendingBehaviour {
	
	@Autowired 
	private SupplyActivityRepository activityRepo; 
	
	@Autowired
	private SupplyContractRepository supplyRep;
	
	@Autowired
	private SupplyActivityStatusRepository statusRepository;
	

	@Override
	public SupplyContractDTO onPendingAction(SupplyContractDTO contract) {
		
		ModelMapper mapper = new ModelMapper();
		SupplyContract contractData = mapper.map(contract, SupplyContract.class);
		
		Integer activityId =  activityRepo.getMaxActivityForContract(contract.getSupplyContractId());
		
		///create the activity 
		SupplyActivity activity = new SupplyActivity();
		activity.setSupplyContractId(contractData.getSupplyContractId());
		activity.setSupplyContractActivityId(activityId);
		activity.setActivityType(ActivityType.M.name());
		activity.setActivityState("P");
		activity.setActivityPrice(contract.getCurrentPrice());	
		activity.setActivityQuantity(contract.getOriginalQuantity());
		activity.setActivityRate(contract.getOriginalRate());
		
		Integer statusId =  statusRepository.getMaxSupplyActivityStatusId();
		
		/// create the status 
		SupplyActivityStatus status = new SupplyActivityStatus();
		status.setActivityType(ActivityType.M.name());
		status.setActivityState("P");
		status.setContractId(contractData.getSupplyContractId());
		status.setContractActivityId(activityId);
		status.setContractActivityStatusId(statusId);
		
		
		//insert activity 
		SupplyActivity activityFromDb = activityRepo.save(activity);
		
		
		///insert status 
		SupplyActivityStatus statusFromDb = statusRepository.save(status);
		
		
		Optional<SupplyContract> optionalContract =	supplyRep.findById(contractData.getSupplyContractId());
			
		
		SupplyContract contractFromDatabase = optionalContract.orElseThrow(IllegalArgumentException :: new);
		
		SupplyContractDTO contractDTO = mapper.map(contractFromDatabase, SupplyContractDTO.class);
		
		SupplyContractActivityDTO activityDTO = mapper.map(activityFromDb, SupplyContractActivityDTO.class);
		
		SupplyContractActivityStatusDTO statusDTO = mapper.map(statusFromDb, SupplyContractActivityStatusDTO.class);
		
		List<SupplyContractActivityStatusDTO> listOfStatus = new ArrayList<SupplyContractActivityStatusDTO>();
		listOfStatus.add(statusDTO);
		activityDTO.setStatuses(listOfStatus);
		
		List<SupplyContractActivityDTO> listOfActivities = new ArrayList<SupplyContractActivityDTO>();
		listOfActivities.add(activityDTO);
		
		
		contractDTO.setActivities(listOfActivities);
		
		
		
		return contractDTO;
	}

}

package com.tradeai.supplycontractservice.service.activity.behaviour;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tradeai.supplycontractservice.data.SupplyActivityRepository;
import com.tradeai.supplycontractservice.data.SupplyActivityStatusRepository;
import com.tradeai.supplycontractservice.data.SupplyContractRepository;
import com.tradeai.supplycontractservice.datamodel.SupplyActivity;
import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;
import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityStatusDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;


@Component("NewPendingActivityBehaviour")
public class NewPendingActivityBehaviour implements PendingBehaviour {
	
	
	@Autowired 
	private SupplyActivityRepository activityRepo; 
	
	@Autowired
	private SupplyContractRepository supplyRep;
	
	@Autowired
	private SupplyActivityStatusRepository statusRepository;
	
	

	@Override
	public SupplyContractDTO onPendingAction(SupplyContractDTO contract) {

		System.out.println("New Pending");
		
		///get a new contract number 
				Integer contractId = supplyRep.getMaxContractId();
				
				if (contractId == null) {
				
					///first record 
					contractId = 1;
				}
				
				contract.setSupplyContractId(contractId);
				
				ModelMapper mapper = new ModelMapper();
				SupplyContract contractData = mapper.map(contract, SupplyContract.class);
				
				
				
				///create the activity 
				SupplyActivity activity = new SupplyActivity();
				activity.setSupplyContractId(contractId);
				activity.setSupplyContractActivityId(new Integer(1));
				activity.setActivityType("N");
				activity.setActivityState("P");
				activity.setActivityPrice(contract.getOriginalPrice());	
				activity.setActivityQuantity(contract.getOriginalQuantity());
				activity.setActivityRate(contract.getOriginalRate());
				
				
				
				
				
				/// create the status 
				SupplyActivityStatus status = new SupplyActivityStatus();
				status.setActivityType("N");
				status.setActivityState("P");
				status.setContractId(contractId);
				status.setContractActivityId(new Integer(1));
				status.setContractActivityStatusId(new Integer(1));



				//insert contract
				SupplyContract contractFromDatabase = supplyRep.save(contractData);
				
				
				//insert activity 
				SupplyActivity activityFromDb = activityRepo.save(activity);
				
				
				///insert status 
				SupplyActivityStatus statusFromDb = statusRepository.save(status);
				
				
				
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

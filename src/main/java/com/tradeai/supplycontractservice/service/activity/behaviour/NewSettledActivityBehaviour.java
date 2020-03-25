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

@Component("NewSettledActivityBehaviour")

public class NewSettledActivityBehaviour implements SettledBehaviour {
	
	
	@Autowired
	private SupplyActivityRepository activityRepo;

	@Autowired
	private SupplyContractRepository supplyRep;

	@Autowired
	private SupplyActivityStatusRepository statusRepository;


	@Override
	public SupplyContractDTO onSettledBehaviour(SupplyContractDTO contract) {

		System.out.println("New Settled");
		
		
		ModelMapper mapper = new ModelMapper();

		SupplyContract contractDomain = mapper.map(contract, SupplyContract.class);

		SupplyActivity activity = activityRepo.findBySupplyContractIdAndActivityTypeAndActivityState(
				contractDomain.getSupplyContractId(), contractDomain.getActivityType(), "P");
		
		activity.setActivityState("S");

		/// create the status
		SupplyActivityStatus status = new SupplyActivityStatus();
		status.setActivityType("N");
		status.setActivityState("S");
		status.setContractId(contractDomain.getSupplyContractId());
		status.setContractActivityId(activity.getSupplyContractActivityId());
		status.setContractActivityStatusId(statusRepository.getMaxSupplyActivityStatusId());

		// TODO Auto-generated method stub
		
		contractDomain = supplyRep.save (contractDomain);
		
		activity = activityRepo.save(activity);
		
		status = statusRepository.save(status);
		
		SupplyContractDTO contractDTO = mapper.map(contractDomain, SupplyContractDTO.class);
		
		SupplyContractActivityDTO activityDTO = mapper.map(activity, SupplyContractActivityDTO.class);
		
		SupplyContractActivityStatusDTO statusDTO = mapper.map(status, SupplyContractActivityStatusDTO.class);
		
		List<SupplyContractActivityStatusDTO> listOfStatus = new ArrayList<SupplyContractActivityStatusDTO>();
		listOfStatus.add(statusDTO);
		activityDTO.setStatuses(listOfStatus);
		
		List<SupplyContractActivityDTO> listOfActivities = new ArrayList<SupplyContractActivityDTO>();
		listOfActivities.add(activityDTO);
		
		
		contractDTO.setActivities(listOfActivities);
		
		
		
		return contractDTO;
		
	}

}

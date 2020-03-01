package com.tradeai.supplycontractservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradeai.supplycontractservice.data.SupplyContractRepository;
import com.tradeai.supplycontractservice.datamodel.SupplyActivity;
import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;
import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityStatusDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;

@Service
public class SupplyContractServiceImpl implements SupplyContractService {
	
	@Autowired
	private SupplyContractRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public SupplyContractDTO getContractByContractId(Integer contractId) {

		Optional<SupplyContract> contract = repository.findById(contractId);
		
		SupplyContract contractForQuery = contract.get();
		
		SupplyContractDTO contractDto = convert(contractForQuery);
		
		return contractDto;
	}
	
	private SupplyContractDTO convert(SupplyContract contractModel) {
		


		
		List<SupplyActivity> activities = contractModel.getActivities();
		
		List<SupplyContractActivityDTO> dtos = new ArrayList<>();
		
		
		
		for (SupplyActivity activity : activities) {
			
			List<SupplyContractActivityStatusDTO> statuses = new ArrayList<>();
			
			 List<SupplyActivityStatus> dbstatus = activity.getStatuses();
			 
			 for (SupplyActivityStatus status : dbstatus) {
				 
				 SupplyContractActivityStatusDTO statusDTO = new SupplyContractActivityStatusDTO();
				 statusDTO.setContractActivityId(status.getRelatedContractActivity().getSupplyContractActivityId());
				 statusDTO.setContractActivityStatusId(status.getContractActivityStatusId());
				 statusDTO.setActivityType(status.getActivityType());
				 statusDTO.setActivityState(status.getActivityState());
				 statuses.add(statusDTO);
				 
			 }
			 
			 
			 SupplyContractActivityDTO activityDTO =  new SupplyContractActivityDTO();
			 
			 activityDTO.setSupplyContractActivityId(activity.getSupplyContractActivityId());
			 activityDTO.setActivityState(activity.getActivityState());
			 activityDTO.setActivityType(activity.getActivityType());
			 
			 activityDTO.setStatuses(statuses);
			 
			 dtos.add(activityDTO);
			 
			 
			 
				 
				 
			
			
		}
		
		SupplyContractDTO contractDTO = new SupplyContractDTO();
		contractDTO.setAccountId(contractModel.getAccountId());
		contractDTO.setSupplyContractId(contractModel.getSupplyContractId());
		contractDTO.setOriginalPrice(contractModel.getOriginalPrice());
		contractDTO.setOriginalQuantity(contractModel.getOriginalQuantity());
		contractDTO.setOriginalRate(contractModel.getOriginalRate());
		contractDTO.setSecurityCode(contractModel.getSecurityCode());
		
		contractDTO.setActivities(dtos);
		


		
		return contractDTO;
		
	}
	
	

}

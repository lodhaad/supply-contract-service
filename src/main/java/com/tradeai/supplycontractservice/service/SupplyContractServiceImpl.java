package com.tradeai.supplycontractservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tradeai.supplycontractservice.data.SupplyActivityRepository;
import com.tradeai.supplycontractservice.data.SupplyActivityStatusRepository;
import com.tradeai.supplycontractservice.data.SupplyContractRepository;
import com.tradeai.supplycontractservice.datamodel.SupplyActivity;
import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;
import com.tradeai.supplycontractservice.datamodel.SupplyContract;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractActivityStatusDTO;
import com.tradeai.supplycontractservice.dto.SupplyContractDTO;
import com.tradeai.supplycontractservice.request.SupplyContractRequest;
import com.tradeai.supplycontractservice.service.activity.processing.ActivityFactory;
import com.tradeai.supplycontractservice.service.activity.processing.SupplyContractActivityType;

@Service
public class SupplyContractServiceImpl implements SupplyContractService {

	@Autowired
	private SupplyContractRepository repository;

	@Autowired
	private SupplyActivityRepository activityRepository;

	@Autowired
	private SupplyActivityStatusRepository acivityStatusRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	@Qualifier("ActivityFactory")
	private ActivityFactory factory;


	@Override
	public SupplyContractDTO processContractActivity(SupplyContractRequest contractRequest, String activityStatus) {
		SupplyContractActivityType activityType = factory.getActivityType(contractRequest);
		
		SupplyContractDTO supplyContractDTO = mapContractRequest (contractRequest);
		
		return activityType.processActivityAndStatus(supplyContractDTO, activityStatus);
	}
	
	
	@Override
	public SupplyContractDTO getContractByContractId(Integer contractId) {

		Optional<SupplyContract> contract = repository.findById(contractId);

		SupplyContract contractForQuery = contract.get();

		SupplyContractDTO contractDto = convert(contractForQuery);

		// SupplyContractResponse response = mapper.map(contractDto, SupplyContractResponse.class);

		return contractDto;
	}

	private SupplyContractDTO convert(SupplyContract contractModel) {

		List<SupplyActivity> activities = activityRepository.findBySupplyContractId(contractModel.getSupplyContractId());

		List<SupplyContractActivityDTO> dtos = new ArrayList<>();

		for (SupplyActivity activity : activities) {

			List<SupplyContractActivityStatusDTO> statuses = new ArrayList<>();

			List<SupplyActivityStatus> dbstatus = acivityStatusRepository.findByContractIdAndContractActivityId(
					activity.getSupplyContractId(), activity.getSupplyContractActivityId());

			for (SupplyActivityStatus status : dbstatus) {

				SupplyContractActivityStatusDTO statusDTO = new SupplyContractActivityStatusDTO();
				statusDTO.setContractActivityId(status.getContractActivityId());
				statusDTO.setContractId(status.getContractId());
				statusDTO.setContractActivityStatusId(status.getContractActivityStatusId());
				statusDTO.setActivityType(status.getActivityType());
				statusDTO.setActivityState(status.getActivityState());

				statuses.add(statusDTO);

			}

			SupplyContractActivityDTO activityDTO = new SupplyContractActivityDTO();

			activityDTO.setSupplyContractActivityId(activity.getSupplyContractActivityId());
			activityDTO.setActivityState(activity.getActivityState());
			activityDTO.setActivityType(activity.getActivityType());
			activityDTO.setContractId(activity.getSupplyContractId());
			activityDTO.setActivityPrice(activity.getActivityPrice());
			activityDTO.setActivityQuantity(activity.getActivityQuantity());
			activityDTO.setActivityRate(activity.getActivityRate());

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
		contractDTO.setSuppliedId(contractModel.getSuppliedId());
		contractDTO.setCurrentPrice(contractModel.getCurrentPrice());
		contractDTO.setCurrentQuantity(contractModel.getCurrentQuantity());
		contractDTO.setCurrentRate(contractModel.getCurrentRate());
		contractDTO.setContractStatus(contractModel.getContractStatus());
		contractDTO.setActivityType(contractModel.getActivityType());

		contractDTO.setActivities(dtos);

		return contractDTO;

	}


}

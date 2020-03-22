package com.tradeai.supplycontractservice.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;

public interface SupplyActivityStatusRepository extends CrudRepository<SupplyActivityStatus, Integer> {
	
	List<SupplyActivityStatus> findByContractIdAndContractActivityId(Integer contractId, Integer contractActivityId);

}

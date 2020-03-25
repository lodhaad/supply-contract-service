package com.tradeai.supplycontractservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;

public interface SupplyActivityStatusRepository extends CrudRepository<SupplyActivityStatus, Integer> {
	
	List<SupplyActivityStatus> findByContractIdAndContractActivityId(Integer contractId, 
			Integer contractActivityId);

	
	@Query("select max(contractActivityStatusId) + 1 from SupplyActivityStatus")
	public Integer getMaxSupplyActivityStatusId();
}

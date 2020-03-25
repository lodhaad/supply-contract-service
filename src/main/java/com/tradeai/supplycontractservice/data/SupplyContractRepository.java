package com.tradeai.supplycontractservice.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tradeai.supplycontractservice.datamodel.SupplyContract;

public interface SupplyContractRepository extends CrudRepository<SupplyContract, Integer> {
	
	@Query("select max(supplyContractId) + 1  from SupplyContract")
	public Integer getMaxContractId();

}

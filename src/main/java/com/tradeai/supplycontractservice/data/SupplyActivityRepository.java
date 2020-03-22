package com.tradeai.supplycontractservice.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tradeai.supplycontractservice.datamodel.SupplyActivity;

public interface SupplyActivityRepository extends CrudRepository<SupplyActivity, Integer> {
	
	public List<SupplyActivity> findBySupplyContractId(Integer supplyContractId);

}

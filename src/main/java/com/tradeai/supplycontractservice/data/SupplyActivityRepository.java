package com.tradeai.supplycontractservice.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tradeai.supplycontractservice.datamodel.SupplyActivity;

public interface SupplyActivityRepository extends CrudRepository<SupplyActivity, Integer> {
	
	public List<SupplyActivity> findBySupplyContractId(Integer supplyContractId);
	
	
	@Query("select max(contractActivityStatusId) + 1 from SupplyActivity where supplyContractId = (:contractId)")
	public Integer getMaxActivityForContract(@Param ("contractId")Integer contractId);
	
	public SupplyActivity findBySupplyContractIdAndActivityTypeAndActivityState(Integer contractId, String activityType, 
			String activityStatus);

}

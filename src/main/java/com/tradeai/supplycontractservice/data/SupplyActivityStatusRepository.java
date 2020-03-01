package com.tradeai.supplycontractservice.data;

import org.springframework.data.repository.CrudRepository;

import com.tradeai.supplycontractservice.datamodel.SupplyActivityStatus;

public interface SupplyActivityStatusRepository extends CrudRepository<SupplyActivityStatus, Integer> {

}

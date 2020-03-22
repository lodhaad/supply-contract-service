package com.tradeai.supplycontractservice.datamodel;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity

@Table (name = "supply_contract_activity_state", schema = "supply")


@IdClass(StatusCompositeKey.class)


public class SupplyActivityStatus {
	
	@Id
	@Column (name = "supply_contract_activity_state_id")
	private Integer contractActivityStatusId;
	
	
	@Id
	@Column (name = "supply_contract_id")
	private Integer contractId;
	
	@Id
	@Column (name = "supply_contract_activity_id")
	private Integer contractActivityId;
	
	
	@Column (name = "supply_contract_activity_type")
	private String activityType;
	
	@Column (name = "supply_contract_activity_state")
	private String activityState;
	
	@Column (name = "supply_activity_state_date")
	private Date activityStateDate;
	
	@Column (name = "supply_contract_activity_state_timestamp")
	private Timestamp activityStateTimestamp;
	
	/*
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
		
	@JoinColumn(name="supply_contract_activity_id", nullable=false, updatable = false) 
	//,
	//@JoinColumn(name="supply_contract_id", nullable=false , updatable = false)
		
	}
	)
	
	
	private SupplyActivity relatedContractActivity;
	
	*/

}

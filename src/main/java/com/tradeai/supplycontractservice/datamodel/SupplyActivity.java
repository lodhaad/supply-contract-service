package com.tradeai.supplycontractservice.datamodel;

import java.sql.Date;
import java.sql.Timestamp;





import javax.persistence.Column;
import javax.persistence.Entity;


import javax.persistence.Id;
import javax.persistence.IdClass;


import javax.persistence.Table;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Entity

@Table (name = "supply_contract_activity", schema = "supply")

@IdClass(ActivityCompositeKey.class)


public class SupplyActivity {
	
	
	
	@Id
	@Column (name = "supply_contract_id")
	private Integer supplyContractId;
	
	
	
	
	
	@Id
	@Column (name = "supply_contract_activity_id")
	private Integer supplyContractActivityId;
	
	
	
	@Column (name = "supply_contract_activity_type")
	private String activityType;
	
	@Column (name = "supply_contract_activity_price")
	private Double activityPrice;
	
	@Column (name = "supply_contract_activity_rate")
	private Double activityRate;
	
	@Column (name = "supply_contract_activity_quantity")
	private Integer activityQuantity;
	
	@Column (name = "supply_contract_activity_state")
	private String activityState;
	
	@Column (name = "supply_contract_activity_date")
	private Date activityDate;
	
	@Column (name = "supply_contract_activity_creation_timestamp")
	private Timestamp activityCreation;
	
	@Column (name = "supply_contract_activity_updation_timestamp")
	private Timestamp activityUpdation;
	
	/*
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="supply_contract_id", nullable=false)
	private SupplyContract relatedContract;
	
	
	
	@OneToMany(mappedBy="relatedContractActivity")
			//,
			//cascade = CascadeType.ALL,
	        //orphanRemoval = true)
	
	private List<SupplyActivityStatus> statuses = new ArrayList<>();
	
	
	public void addStatuses(SupplyActivityStatus status) {
		statuses.add(status);
		status.setRelatedContractActivity(this);
	    }
	




	 
	    public void removeStatuses(SupplyActivityStatus account) {
	    	statuses.remove(account);
	    	account.setRelatedContractActivity(null);
	    }
	    
	    */
	




}

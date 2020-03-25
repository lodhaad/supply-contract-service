package com.tradeai.supplycontractservice.datamodel;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString





@Entity

@Table (name = "supply_contract", schema = "supply")

public class SupplyContract {
	
	@Id
	@Column (name = "supply_contract_id")
	private Integer supplyContractId;
	
	
	@Column (name = "supplier_id")
	private String suppliedId;
	
	@Column (name = "supply_sec_code")
	private String securityCode;
	
	@Column (name = "supply_orig_qty")
	private Integer originalQuantity;
	
	@Column (name = "acct_id")
	private String accountId;
	
	@Column (name = "supply_current_qty")
	private Integer currentQuantity;
	
	@Column (name = "supply_orig_rate")
	private Double originalRate;
	
	@Column (name = "supply_current_rate")
	private Double currentRate;
	
	@Column (name = "supply_orig_price")
	private Double originalPrice;
	
	@Column (name = "supply_current_price")
	private Double currentPrice;
	
	@Column (name = "supply_contract_date")
	private Date contractBookingDate;
	
	@Column (name = "contract_creation")
	private Timestamp timestamp;
	
	@Column (name = "contract_status")
	private String contractStatus;
	
	@Column (name = "activity_type")
	private String activityType;
	
	
	
	///add the type of activity and the status and drive everything from it
	
	/*
	  @OneToMany(mappedBy="relatedContract")
	 
	  private List<SupplyActivity> activities = new ArrayList<>();
	
	
	public void addActivity(SupplyActivity activity) {
		activities.add(activity);
		activity.setRelatedContract(this);
	    }
	


	 
	    public void removeActivity(SupplyActivity account) {
	    	activities.remove(account);
	        account.setRelatedContract(null);
	    }
	
	
	
	 
	 
	 
	 */
	
	
	
	

}

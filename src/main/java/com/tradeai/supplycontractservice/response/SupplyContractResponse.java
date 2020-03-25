package com.tradeai.supplycontractservice.response;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;




import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class SupplyContractResponse {
	
	private Integer supplyContractId;

	private String suppliedId;

	private String securityCode;

	private Integer originalQuantity;

	private String accountId;

	private Integer currentQuantity;

	private Double originalRate;

	private Double currentRate;

	private Double originalPrice;

	private Double currentPrice;

	private Date contractBookingDate;

	 private LocalDate timestamp;

	private String contractStatus;
	
	private String activityType;
	
	private List<SupplyContractActivityResponse> activities;

	


}

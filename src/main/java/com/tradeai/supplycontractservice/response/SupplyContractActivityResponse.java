package com.tradeai.supplycontractservice.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SupplyContractActivityResponse {

	private Integer supplyContractActivityId;

	private Integer contractId;

	private String activityType;

	private Double activityPrice;

	private Double activityRate;

	private Integer activityQuantity;

	private String activityState;

	private Date activityDate;

	private LocalDateTime activityCreation;

	private LocalDateTime activityUpdation;

	private List<SupplyContractActivityStatusResponse> statuses;

}
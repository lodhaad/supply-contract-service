package com.tradeai.supplycontractservice.dto;



import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.util.Date;


@Getter
@Setter

public class SupplyContractActivityStatusDTO {

	private Integer contractActivityStatusId;

	private Integer contractId;

	private Integer contractActivityId;

	private String activityType;

	private String activityState;

	private Date activityStateDate;

	private LocalDateTime activityStateTimestamp;

}

package com.tradeai.supplycontractservice.datamodel;

import java.io.Serializable;

public class ActivityCompositeKey implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer supplyContractId;
	private Integer supplyContractActivityId;
	
	
	
	
	public ActivityCompositeKey() {


	}




	public ActivityCompositeKey(Integer supplyContractId, Integer supplyContractActivityId) {
		super();
		this.supplyContractId = supplyContractId;
		this.supplyContractActivityId = supplyContractActivityId;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((supplyContractActivityId == null) ? 0 : supplyContractActivityId.hashCode());
		result = prime * result + ((supplyContractId == null) ? 0 : supplyContractId.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActivityCompositeKey other = (ActivityCompositeKey) obj;
		if (supplyContractActivityId == null) {
			if (other.supplyContractActivityId != null)
				return false;
		} else if (!supplyContractActivityId.equals(other.supplyContractActivityId))
			return false;
		if (supplyContractId == null) {
			if (other.supplyContractId != null)
				return false;
		} else if (!supplyContractId.equals(other.supplyContractId))
			return false;
		return true;
	}
	
	
	
	
	

}

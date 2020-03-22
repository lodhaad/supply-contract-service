package com.tradeai.supplycontractservice.datamodel;

import java.io.Serializable;

public class StatusCompositeKey implements Serializable {
	


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer contractActivityStatusId;
	private Integer contractId;
	private Integer contractActivityId;
	
	
	public StatusCompositeKey(Integer contractActivityStatusId, Integer contractId, Integer contractActivityId) {
		super();
		this.contractActivityStatusId = contractActivityStatusId;
		this.contractId = contractId;
		this.contractActivityId = contractActivityId;
	}


	public StatusCompositeKey() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contractActivityId == null) ? 0 : contractActivityId.hashCode());
		result = prime * result + ((contractActivityStatusId == null) ? 0 : contractActivityStatusId.hashCode());
		result = prime * result + ((contractId == null) ? 0 : contractId.hashCode());
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
		StatusCompositeKey other = (StatusCompositeKey) obj;
		if (contractActivityId == null) {
			if (other.contractActivityId != null)
				return false;
		} else if (!contractActivityId.equals(other.contractActivityId))
			return false;
		if (contractActivityStatusId == null) {
			if (other.contractActivityStatusId != null)
				return false;
		} else if (!contractActivityStatusId.equals(other.contractActivityStatusId))
			return false;
		if (contractId == null) {
			if (other.contractId != null)
				return false;
		} else if (!contractId.equals(other.contractId))
			return false;
		return true;
	}
	
	
	
	

}

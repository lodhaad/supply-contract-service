package com.tradeai.supplycontractservice.constants;

public enum ActivityType {
	
	N("New"),
	M("Mark"),
	R("Return"),
	P("Partial Return");
	
	 private final String fullName;   // in kilograms
	
	private ActivityType(String fullName) {
		this.fullName = fullName;
	}

}

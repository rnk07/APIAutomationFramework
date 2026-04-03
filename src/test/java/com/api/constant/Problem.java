package com.api.constant;

public enum Problem {

	 POOR_BATTERY_LIFE(1),  
	 SMARTPHONE_IS_RUNNING_SLOW(2),
	 SYNC_ISSUE(3);
	 
	 int code;
	
	private Problem(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}

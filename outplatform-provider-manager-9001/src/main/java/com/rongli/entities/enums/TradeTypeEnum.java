package com.rongli.entities.enums;

public enum TradeTypeEnum {
	payment("0","消费"),
	refund("1","退费");
	private String code;
	private String desc;
	private TradeTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}

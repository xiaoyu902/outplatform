package com.rongli.entities.enums;

public enum ActiveEnum {
	isActive("1","生效"),
	notActive("0","失效");
	private String code,desc;

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

	private ActiveEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	

	
}

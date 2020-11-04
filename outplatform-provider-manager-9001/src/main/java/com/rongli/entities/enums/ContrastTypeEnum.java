package com.rongli.entities.enums;

public enum ContrastTypeEnum {
	hand("1","手动"),
	auto("0","自动");
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

	private ContrastTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
}

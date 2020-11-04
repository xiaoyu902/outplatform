package com.rongli.entities.enums;

public enum ContrastResultEnum {
	success("0","成功"),
	failure("1","失败");
	private String code,desc;

	private ContrastResultEnum(String code, String desc) {
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

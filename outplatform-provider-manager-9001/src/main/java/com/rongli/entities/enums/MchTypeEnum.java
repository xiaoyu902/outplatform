package com.rongli.entities.enums;

public enum MchTypeEnum {
	normal("normal","参与对账类型"),
	special("special","特殊账单类型");
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

	private MchTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	
	

	
}

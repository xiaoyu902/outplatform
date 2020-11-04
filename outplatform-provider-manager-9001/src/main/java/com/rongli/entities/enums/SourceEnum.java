package com.rongli.entities.enums;

public enum SourceEnum {
	mch("mch","商户来源"),
	org("org","机构来源");
	
	
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

	private SourceEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;

	}
}

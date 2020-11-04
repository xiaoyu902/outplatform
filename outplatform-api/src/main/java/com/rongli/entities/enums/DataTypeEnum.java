package com.rongli.entities.enums;

public enum DataTypeEnum {
	JSON("json","JSON参数"),
	XML("xml","xml参数");
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

	private DataTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}

package com.rongli.entities.enums;

public enum ResultTypeEnum {
	longtype("l","长款","#EEAD0E"),
	normaltype("n","平账","#C1FFC1"),
	shorttype("s","短款","#EE4000");
	
	
	private String code,desc,color;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private ResultTypeEnum(String code, String desc, String color) {
		this.code = code;
		this.desc = desc;
		this.color = color;
	}

	
}

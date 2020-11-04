package com.rongli.entities.enums;

public enum PayTypeEnum {
	cash("cash","现金"),
	union("union","银联"),
	wx("wx","微信"),
	yb("yb","医保"),
	zfb("zfb","支付宝");
	
	private String code;
	private String desc;
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
	private PayTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	
}

package com.rongli.entities.enums;

public enum ProcessEnum {
	wait("wait","待审核"),
	adopt("adopt","通过"),
	ignore("ignore","忽略"),
	normal("normal","平账"),
	pend("pend","待处理"),
	refuse("refuse","拒绝");
	
	
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
	private ProcessEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	
	
	
	
}

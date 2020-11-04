package com.rongli.entities.enums;

public enum ApplyTypeEnum {
	abnormal("abnormal","异常登记"),
	refund("refund","退费申请");
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

	private ApplyTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}

package com.rongli.entities.enums;

public enum ReasonEnum {
	normal("订单正常"),
	communication_confirmed("跟患者沟通确认"),
	network_delay("网络延迟"),
	other("其他"),
	application_for_refund("患者申请实时退费");
	
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private ReasonEnum(String reason) {
		this.reason = reason;
	}

	
	
}

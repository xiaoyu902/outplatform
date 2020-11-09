package com.rongli.entities.params;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_api_recharge")
public class Recharge implements Serializable {

	private static final long serialVersionUID = 3922745716859706097L;

	@TableId
	private String orderId;
	
	private String tradeMessage,bankCardNo,chargeTime,transactionNo;
	
	private String amount,payType,admissionNo,operatorId,termId,patientId,medicalNo;
	
	private String certificateNo,cardNo,name,remark,balance,tradeTime;
	
	private Integer tradeResult,payChannel,certificateType,cardType;
}

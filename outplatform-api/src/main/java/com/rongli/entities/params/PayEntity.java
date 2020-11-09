package com.rongli.entities.params;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_api_pay")
public class PayEntity implements Serializable {

	private static final long serialVersionUID = -7393528585395827806L;

	@TableId
	private String orderId;
	
	private String tradeMessage,billId,visitNo,bankCardNo,transactionNo,payTime;
	
	private String totalFee,changeFee,payableFee,medicareFee,medFelfFee,personFee;
	
	private String payType,medicareParams,operatorId,termId,patientId,medicalNo;
	
	private String certificateNo,cardNo,name,receiptId,invoiceId,remark,tradeTime;
	
	private Integer tradeResult,channelType,feeType,certificateType,cardType;
}

package com.rongli.entities.params;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("v_api_register")
public class Register implements Serializable {

	private static final long serialVersionUID = 2964957932061057873L;

	@TableId
	private String orderId;
	
	private String tradeMessage,hospOrderId,bankCardNo,transactionNo,payTime;
	
	private String totalFee,changeFee,payableFee,medicareFee,medFelfFee,personFee;
	
	private String medicareParameter,operatorId,termId,patientId,medicalNo,certificateNo;
	
	private String cardNo,scheduleId,timeId,departmentName,doctorName,departmentId;
	
	private String doctorId,registerDate,startTime,endTime,levelCode,name,receiptId;
	
	private String invoiceId,sequenceNo,visitAddress,remark,tradeTime;
	
	private Integer tradeResult,payChannel,feeType,certificateType,cardType,noon,visitFlag;
}









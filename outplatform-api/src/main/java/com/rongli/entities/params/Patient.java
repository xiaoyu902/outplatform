package com.rongli.entities.params;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_api_create")
public class Patient {

	private Integer tradeResult,cardType,patientType,certificateType;
	
	private String tradeMessage,cardNo,certificateNo,guardCert_no,guardName;
	
	private String name,birthday,address,mobile,nation,photo;
	
	private Integer guardCertType,sex,country,createHealthCard;
	
	private String tradeTime,patientId,medicalNo,qrCodeText,termId;
	
}

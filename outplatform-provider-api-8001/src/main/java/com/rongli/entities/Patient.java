package com.rongli.entities;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_patient")
public class Patient {
	private String cardType;
	private String cardNo;
	private String patientType;
	private String certificateType;
	private String certificateNo;
	private String guardCertType;
	private String guardCertNo;
	private String guardName;
	private String name;
	private String sex;
	private String birthday;
	private String address;
	private String mobile;
	private String country;
	private String photo;
	private String createHealthCard;
	private String createTime;
	private String patientId;
	private String medicalNo;
	private String qrCodeText;
}

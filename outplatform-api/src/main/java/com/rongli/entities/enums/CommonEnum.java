package com.rongli.entities.enums;

import com.rongli.exception.BaseErrorInfoInterface;

public enum CommonEnum implements BaseErrorInfoInterface{
	// 数据操作错误定义
		SUCCESS("0", "SUCCESS"), 
		FALSE("1","FALSE"),
		ERROR("-1", "ERROR"), 
		BODY_NOT_MATCH("400","空指针异常!"),
		SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),
		NOT_FOUND("404", "未找到该资源!"), 
		INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
		NO_ACCESS("403", "没有权限访问!请重新认证登录!"),
		SERVER_BUSY("503","服务器正忙，请稍后再试!")
		;

		/** 错误码 */
		private String resultCode;

		/** 错误描述 */
		private String resultMsg;

		CommonEnum(String resultCode, String resultMsg) {
			this.resultCode = resultCode;
			this.resultMsg = resultMsg;
		}

		@Override
		public String getResultCode() {
			return resultCode;
		}

		@Override
		public String getResultMsg() {
			return resultMsg;
		}
}

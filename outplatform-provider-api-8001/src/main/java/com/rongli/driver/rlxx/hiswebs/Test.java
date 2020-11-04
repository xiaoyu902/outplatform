package com.rongli.driver.rlxx.hiswebs;

public class Test {
	public static void main(String[] args) {
		HisServiceImpService his = new HisServiceImpService();
		HisService hisService = his.getHisServiceImpPort();
		String str = hisService.getPatientInfo(""); 
		System.out.println("返回："+str);
	}
}

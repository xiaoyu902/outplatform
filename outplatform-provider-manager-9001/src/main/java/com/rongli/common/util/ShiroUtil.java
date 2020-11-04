package com.rongli.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class ShiroUtil {

	public static Object getAttribute(String key) {
		Session session=SecurityUtils.getSubject().getSession();
		Object obj=session.getAttribute(key);
		return obj;
	}
	
	public static void setAttribute(String key,Object value) {
		Session session=SecurityUtils.getSubject().getSession();
		session.setAttribute(key, value);
	}
	
	public static void removeAttribute(String key) {
		Session session=SecurityUtils.getSubject().getSession();
		session.removeAttribute(key);
	}
	
}

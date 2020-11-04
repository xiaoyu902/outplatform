package com.rongli.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.rongli.common.exception.BaseException;
import com.rongli.entities.ResultBody;
import com.rongli.entities.enums.CommonEnum;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class WholeExceptionHandler {
	
	@ExceptionHandler(value = BaseException.class)
	@ResponseBody
	public ResultBody exceptionHandler(BaseException e){
		log.error("发生业务异常!"+e.toString());
       	return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
    }
	
	
	@ExceptionHandler(value = NullPointerException.class)
	@ResponseBody
	public ResultBody exceptionHandler(NullPointerException e){
		log.error("发生空指针异常!"+e.toString());
       	return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }
	
	/**
     * 处理其他异常
  * @param req
  * @param e
  * @return
  */
	@ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
 		log.error("未知异常！原因是:",e.getMessage());
    	return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
	}
	
	 @ExceptionHandler(value = UnauthorizedException.class)
	 @ResponseBody
	 public Object Unauthorized(HttpServletRequest reqest, HttpServletResponse response,Exception e){
		 boolean isAjax = isAjax(reqest);
		 log.error("未认证抛出异常", e.getMessage());
	        if (isAjax) {
	        	return ResultBody.error(CommonEnum.NO_ACCESS);
	        } else {
	            
	            ModelAndView mv = new ModelAndView();
	            mv.addObject("message", CommonEnum.NO_ACCESS.getResultMsg());
	            mv.setViewName("error/403");
	            return mv;
	        }
	 }
	 
	 /**
	     * 判断网络请求是否为ajax
	     *
	     * @param req
	     * @return
	     */
    private boolean isAjax(HttpServletRequest req) {
        String contentTypeHeader = req.getHeader("Content-Type");
        String acceptHeader = req.getHeader("Accept");
        String xRequestedWith = req.getHeader("X-Requested-With");
        return (contentTypeHeader != null && contentTypeHeader.contains("application/json"))
                || (acceptHeader != null && acceptHeader.contains("application/json"))
                || "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
    }
}

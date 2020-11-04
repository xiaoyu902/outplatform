package com.rongli.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.rongli.entities.ResultBody;
import com.rongli.entities.enums.CommonEnum;
import com.rongli.exception.BaseException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
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
       	return ResultBody.error(CommonEnum.BODY_NOT_MATCH.getResultMsg());
    }
	
	/**
     * 处理其他异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NoHandlerFoundException.class)
	@ResponseBody
	public ResultBody noHandlerFoundException(HttpServletRequest req, Exception e){
 		log.error("404！原因是:",e);
    	return ResultBody.error(CommonEnum.NOT_FOUND.getResultMsg()+":"+e.getMessage());
	}
	
	@ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResultBody exceptionHandler(HttpServletRequest req, Exception e){
 		log.error("未知异常！原因是:",e);
    	return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR.getResultMsg()+":"+e.getMessage());
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

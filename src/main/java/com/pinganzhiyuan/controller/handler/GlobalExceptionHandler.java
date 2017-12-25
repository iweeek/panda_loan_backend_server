package com.pinganzhiyuan.controller.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pinganzhiyuan.util.ResponseUtil;


/**
* @ClassName: GlobalExceptionHandler
* @Description: 捕获处理所有未处理异常
* @author x1ny
* @date 2017年5月9日
*/
//@ControllerAdvice
//@ResponseBody
//public class GlobalExceptionHandler {
//	private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
//
//	@ExceptionHandler(Exception.class)
//	public String handler(Exception exception) {
//		logger.error(exception);
//		return ResponseUtil.standardResponse(new String[]{"发生了未知异常"}, null);
//	}
//}

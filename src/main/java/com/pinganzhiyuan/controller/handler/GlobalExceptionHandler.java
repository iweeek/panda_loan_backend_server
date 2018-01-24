package com.pinganzhiyuan.controller.handler;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pinganzhiyuan.util.ResponseUtil;

import io.jsonwebtoken.ExpiredJwtException;


/**
* @ClassName: GlobalExceptionHandler
* @Description: 捕获处理所有未处理异常
* @author x1ny
* @date 2017年5月9日
*/

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	private final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

	String logMsg = "";
    /**
    * @author x1ny
    * @date 2017年5月9日
    * @Description: 处理所有未知的异常
    * @param exception
    * @return
    * @throws
    */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handler(Exception exception) {
        com.pinganzhiyuan.util.ResponseBody resBody = new com.pinganzhiyuan.util.ResponseBody();
        exception.printStackTrace();
        if (exception instanceof ExpiredJwtException) {
            resBody.statusMsg = "对不起，您没有权限访问";
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(resBody);
        } else if (exception instanceof UnauthorizedException) {
            resBody.statusMsg = "对不起，您没有权限访问";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resBody);
        } else if (exception instanceof UnauthenticatedException) {
            resBody.statusMsg = "对不起，您还未登录";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resBody);
        } else if (exception instanceof ExcessiveAttemptsException) {
            resBody.statusMsg = "对不起，您尝试登录的次数过多";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(resBody);
        } else {
            resBody.statusMsg = "对不起，服务器开小差啦";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
        }
    }
}

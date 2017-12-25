package com.pinganzhiyuan.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;

public class DeviceInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(DeviceInterceptor.class);
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
	    
	        String version = request.getHeader("Version");
	        String userId = request.getHeader("User-Id");
	        String userAgent = request.getHeader("User-Agent");
	        String ip = request.getRemoteAddr();
	       
	        String uri = request.getRequestURI();
	        if (version == null || userId == null) {
	            return false;
	        }
	        
	        DeviceLog deviceLog = new DeviceLog();
	        deviceLog.setVersion(Integer.valueOf(userId));
	        deviceLog.setUserId(Long.valueOf(userId));
	        deviceLog.setUserAgent(userAgent);
	        deviceLog.setIp(ip);
	        
	        deviceLog.setUri(uri);
	        
	        deviceLogMapper.insert(deviceLog);
	        

		return super.preHandle(request, response, handler);
	}

}

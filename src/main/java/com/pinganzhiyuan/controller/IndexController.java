package com.pinganzhiyuan.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.LenderAccessLogMapper;
import com.pinganzhiyuan.model.LenderAccessLog;

@RestController
public class IndexController {
    
    @Autowired
    private LenderAccessLogMapper lenderAccessLogMapper;
    
    @RequestMapping(value="/record", method = RequestMethod.GET)
    public void read(HttpServletRequest request, HttpServletResponse response) {
        
//        String deviceId = request.getHeader("Device-Id");
//        String userId = request.getHeader("User-Id");
        String redirectUri = request.getParameter("redirect");
        if (redirectUri == null) {
        } else {
            try {
                LenderAccessLog log = new LenderAccessLog();
                log.setLenderUrl(redirectUri);
//                log.setDeviceId(deviceId);
//                log.setUserId(Long.valueOf(userId));
                lenderAccessLogMapper.insert(log);
                response.sendRedirect(redirectUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    

}

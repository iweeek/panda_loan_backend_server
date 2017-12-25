package com.pinganzhiyuan.service.impl;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.CaptchaMapper;
import com.pinganzhiyuan.model.Captcha;
import com.pinganzhiyuan.model.CaptchaExample;
import com.pinganzhiyuan.service.CaptchaService;
import com.pinganzhiyuan.util.CaptchaUtil;
import com.pinganzhiyuan.util.WebUtils;

@Service
public class CaptchaServiceImpl implements CaptchaService{
    
    @Autowired
    CaptchaMapper captchaMapper;
    
    @Override
    public BufferedImage genCaptcha(HttpServletResponse response) {
        // cookie, 默认30分钟
        String cookieId = UUID.randomUUID().toString().replace("-", "").toUpperCase(); 
        WebUtils.addCookie(response, "Cookie", cookieId, 30);  
        
        //生成一个校验码  
        String genCaptcha = CaptchaUtil.genCaptcha(5);  
        Captcha captcha = new Captcha();
        captcha.setCookie(cookieId);
        captcha.setCaptcha(genCaptcha);
        captcha.setExpired(30);
        captcha.setIsPassed((byte) 0);
        
        captchaMapper.insert(captcha);
        
        //把校验码转为图像  
        BufferedImage image = CaptchaUtil.genCaptchaImg(genCaptcha);  
        return image;  
    }
    
    @Override  
    public Boolean verifyCaptcha(HttpServletRequest request, String captcha, String key) {  
          
        if(!StringUtils.isEmpty(captcha)){  
            String cookieId = key;  
            // 从数据库里取出cookie判断是否匹配
            CaptchaExample example = new CaptchaExample();
            example.createCriteria().andCookieEqualTo(cookieId);
            List<Captcha> list = captchaMapper.selectByExample(example);
            if (list.size() > 0) {
                Captcha capt = list.get(0);
                if (captcha.toUpperCase().equals(capt.getCaptcha())) {
                    capt.setIsPassed((byte) 1);
                    captchaMapper.updateByPrimaryKey(capt);
                    return true;
                } else {
                    capt.setIsPassed((byte) 0);
                    captchaMapper.updateByPrimaryKey(capt);
                    return false;
                }
            } else {
                return false;
            }
        }  
        return false;
    }

    @Override
    public Boolean verifyCaptchaIsPassed(HttpServletRequest request, Captcha captcha, String key) {
//        String cookieId = WebUtils.getCookieByName(request, "Cookie");  
        String cookieId = key;
        // 从数据库里取出cookie判断是否匹配
        CaptchaExample example = new CaptchaExample();
        example.createCriteria().andCookieEqualTo(cookieId);
        List<Captcha> list = captchaMapper.selectByExample(example);
        if (list.size() > 0) {
            Captcha capt = list.get(0);
            if (capt.getIsPassed() == 1) {
                captcha.setId(capt.getId());;
                // 删除这个验证码记录
//                captchaMapper.deleteByPrimaryKey(capt.getId());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }  
}

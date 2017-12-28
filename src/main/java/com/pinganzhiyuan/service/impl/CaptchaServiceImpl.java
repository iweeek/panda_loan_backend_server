package com.pinganzhiyuan.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pinganzhiyuan.mapper.CaptchaMapper;
import com.pinganzhiyuan.mapper.SMSLogMapper;
import com.pinganzhiyuan.model.Captcha;
import com.pinganzhiyuan.model.CaptchaExample;
import com.pinganzhiyuan.model.SMSLog;
import com.pinganzhiyuan.model.SMSLogExample;
import com.pinganzhiyuan.service.CaptchaService;
import com.pinganzhiyuan.util.CaptchaUtil;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    CaptchaMapper captchaMapper;
    
    @Autowired
    SMSLogMapper smsLogMapper;

    @Override
    public Captcha genCaptcha(int tag) {
        // 生成一个校验码
        String strCaptcha;
        if (tag == 1) {
            strCaptcha = CaptchaUtil.getHybridChars(4);
        } else {
            strCaptcha = CaptchaUtil.getNumChars(4);
        }
        
        Captcha captcha = new Captcha();
        captcha.setCaptcha(strCaptcha);
        captcha.setAppliedTime(new Date());
        captcha.setIsExpired(false);

        captchaMapper.insert(captcha);
        
        return captcha;

    }
    
    @Override
    public Boolean verifyCaptcha(String captcha, String key) {
        if (!StringUtils.isEmpty(captcha)) {
            CaptchaExample example = new CaptchaExample();
            example.createCriteria().andIdEqualTo(Long.valueOf(key)).andIsExpiredEqualTo(false);
            List<Captcha> list = captchaMapper.selectByExample(example);
            if (list.size() > 0) {
                Captcha capt = list.get(0);
                if (captcha.toUpperCase().equals(capt.getCaptcha())) {
                    capt.setIsExpired(true);
                    captchaMapper.updateByPrimaryKey(capt);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public SMSLog getLastSMSByPhone(String phone) {
        SMSLogExample example = new SMSLogExample();
        example.createCriteria().andPhoneEqualTo(phone);
        
        example.setOrderByClause("send_time desc");
        List<SMSLog> list = smsLogMapper.selectByExample(example);
        
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public int obsoleteCaptchaByPhone(String phone) {
//        return captchaMapper.obsoleteSMSsByPhone(phone);
        return 0;
    }

}

package com.pinganzhiyuan.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.model.Captcha;
import com.pinganzhiyuan.service.CaptchaService;
import com.pinganzhiyuan.util.CaptchaUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class ImageCaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @ApiOperation(value = "创建验证码", notes = "创建验证码，将创建好的验证码存入数据库中")
    @RequestMapping(value = { "/imageCaptchas" }, method = RequestMethod.POST)
    public void create(HttpServletResponse response) {
        try {
            // 把校验码转为图像
            Captcha captcha = captchaService.genCaptcha(1);
            BufferedImage image = CaptchaUtil.genCaptchaImg(captcha.getCaptcha());

            response.setContentType("image/jpeg");
            response.addHeader("keyImageCapt", String.valueOf(captcha.getId()));
            System.out.println(response);
            // 输出图像
            ServletOutputStream outStream = response.getOutputStream();
            ImageIO.write(image, "jpeg", outStream);
            
            response.flushBuffer();
            outStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

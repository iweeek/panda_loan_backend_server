package com.pinganzhiyuan.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.service.CaptchaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建验证码", notes = "创建验证码，将创建好的验证码存入数据库中")
    @RequestMapping(value = { "/captcha" }, method = RequestMethod.GET)
    public void create(HttpServletResponse response) {

        try {
            // 把校验码转为图像
            BufferedImage image = captchaService.genCaptcha(response);

            response.setContentType("image/jpeg");
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
    

    /**
     * 检查验证码是否正确
     * 
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = {"/verifyCaptcha"}, method = RequestMethod.POST)
    public ResponseEntity<?> verifyCaptcha(HttpServletRequest request, @ApiParam("验证码") @RequestParam String captcha,
            @RequestParam String key) {        Cookie[] cookies = request.getCookies();
        Boolean verifyCaptcha = captchaService.verifyCaptcha(request, captcha, key);
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(verifyCaptcha);
    }

}

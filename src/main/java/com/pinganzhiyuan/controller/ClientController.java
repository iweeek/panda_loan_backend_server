package com.pinganzhiyuan.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.pinganzhiyuan.mapper.CaptchaMapper;
import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.IdVerificationMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Captcha;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.IdVerification;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.service.CaptchaService;
import com.pinganzhiyuan.service.TokenService;
import com.pinganzhiyuan.util.IdVerifUtil;
import com.pinganzhiyuan.util.ResponseBody;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
public class ClientController {
    
    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private CaptchaService captchaService;
    
    @Autowired
    CaptchaMapper captchaMapper;
    
    @Autowired
    IdVerificationMapper idVerificationMapper;
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    ClientMapper clientMapper;
    
    @ApiOperation(value = "创建客户", notes = "创建客户")
    @RequestMapping(value = { "/clients" }, method = RequestMethod.POST)
    public ResponseEntity<?> create(@ApiParam("用户Id，必填") @RequestParam(required = false) Long userId, @ApiParam("真实姓名，必填")@RequestParam String name,
            @ApiParam("身份证号，必填")@RequestParam String idNo, @ApiParam("学历，选填")@RequestParam(required = false) String edu,
            @ApiParam("信用资质，选填")@RequestParam(required = false) String guarantee, @ApiParam("职业，选填")@RequestParam(required = false) String profession,
            @ApiParam("居住地址，选填")@RequestParam(required = false) String resiAddr,
            @ApiParam("性别，选填")@RequestParam(required = false) Boolean isMan, @ApiParam("民族，选填")@RequestParam(required = false) String nation,
            @ApiParam("生日，选填")@RequestParam(required = false) @DateTimeFormat(pattern="yyyyMMdd") Date birthday, @ApiParam("身份证签发机关，选填")@RequestParam(required = false) String auth,
            @ApiParam("身份证过期时间，选填")@RequestParam(required = false) @DateTimeFormat(pattern="yyyyMMdd") Date expirDate, HttpServletRequest request) {
        
        if (!verifyIdentity(name, idNo)) {
            return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).body(null); 
        }
        
        ClientExample example = new ClientExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<Client> list = clientMapper.selectByExample(example);
        if (list.size() > 0) {
            return ResponseEntity.status(HttpServletResponse.SC_CONFLICT).body(null); 
        }
        
        Client client = new Client();
        client.setUserId(userId);
        client.setName(name);
        client.setIdentityNo(idNo);
        
        if (edu != null) {
            client.setEducation(edu);
        }
        
        if (guarantee != null) {
            client.setGuarantee(guarantee);
        }
        
        if (profession != null) {
            client.setProfession(profession);;
        }
        
        if (resiAddr != null) {
            client.setResidenceAddr(resiAddr);
        }
        
        if (isMan != null) {
            client.setIsMan(isMan);
        }
        
        if (nation != null) {
            client.setNation(nation);
        }
        
        if (birthday != null) {
            client.setBirthday(birthday);
        }
        
        if (auth != null) {
            client.setIdentityAuth(auth);
        }
        
        if (expirDate != null) {
            client.setIdentityExpiration(expirDate);
        }
        
        int ret = clientMapper.insertSelective(client);
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
    
    @ApiOperation(value = "查询客户信息", notes = "查询客户信息")
    @RequestMapping(value = { "/client/{id}" }, method = RequestMethod.GET)
    public ResponseEntity<?> read(@ApiParam("客户Id") @PathVariable Long id) {
        
        Client client = clientMapper.selectByPrimaryKey(id);
        @SuppressWarnings("rawtypes")
        ResponseBody body = new ResponseBody();
        body.statusMsg = "操作成功";
        body.obj1 = client;
        if (client != null) {
            return ResponseEntity.status(HttpServletResponse.SC_OK).body(client); 
        } else {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(null); 
        }
    }
    
    Boolean verifyIdentity(String name, String id) {
        String result = "";
        try {
            result = IdVerifUtil.sendIdVerf(name, id, "noPhoto");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
        
        JSONObject obj = new JSONObject(result);
        try {
            JSONObject data = obj.getJSONObject("data");
            
            IdVerification idVerif = new IdVerification();
            idVerif.setName(name);
            idVerif.setIdentity(id);
            idVerif.setCode(data.getString("code"));
            idVerif.setDescription(data.getString("desc"));
            idVerif.setPhoto(data.getString("photo"));
            idVerif.setTransId(data.getString("trans_id"));
            idVerif.setTradeNo(data.getString("trade_no"));
            idVerif.setFee(data.getString("fee"));
            
            idVerificationMapper.insert(idVerif);
        
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

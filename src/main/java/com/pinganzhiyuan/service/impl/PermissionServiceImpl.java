package com.pinganzhiyuan.service.impl;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.PermissionMapper;
import com.pinganzhiyuan.model.Permission;
import com.pinganzhiyuan.service.PermissionService;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
* TokenService 实现类.
* 
* @author x1ny
* @date 2017年5月22日
*/
@Service
public class PermissionServiceImpl implements PermissionService {
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);
	
	String logMsg = "";
	
   @Autowired
    private PermissionMapper privMapper;

    @Override
    public int create(Permission role) {
        return 0;
    }
    
    @Override
    public int update(Permission role) {
        return 0;
    }
    
    @Override
    public int delete(Long id) {
        return 0;
    }
    
    @Override
    public Permission read(Long id) {
        return privMapper.selectByPrimaryKey(id);
    }
    
    @Override
    public List<Permission> index() {
        return null;
    }
}

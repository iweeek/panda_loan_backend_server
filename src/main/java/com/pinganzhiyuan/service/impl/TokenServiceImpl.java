package com.pinganzhiyuan.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.dto.TokenDTO;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.UserBackendMapper;
import com.pinganzhiyuan.model.UserBackend;
import com.pinganzhiyuan.model.UserBackendExample;
import com.pinganzhiyuan.service.TokenService;
import com.pinganzhiyuan.service.UserService;
import com.pinganzhiyuan.util.ResponseBody;

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
public class TokenServiceImpl implements TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	
	String logMsg = "";
	
	/**
	 * jwt加密、解密的密匙
	 */
	private final String KEY;
	
	@Autowired
	private UserBackendMapper userBackendMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	public TokenServiceImpl(@Value("${jwt.key}") String key) {		
		KEY = key;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int create(String username, String password, Integer expiredHour, String userAgent, ResponseBody resBody) {
	    UserBackendExample example = new UserBackendExample();
	    example.createCriteria().andUsernameEqualTo(username);
		List<UserBackend> user = userBackendMapper.selectByExample(example);
		
		if (user == null || user.size() == 0) {
            logMsg = "没有找到这个用户";
            logger.error(logMsg);
            
            resBody.statusMsg = logMsg;
            resBody.obj1 = null;
            
            return HttpServletResponse.SC_NOT_FOUND;
        } else if (!user.get(0).getPassword().equals(password)) {
            logMsg = "用户名和密码不匹配";
            logger.error(logMsg);
            
            resBody.statusMsg = logMsg;
            resBody.obj1 = null;
            
            return HttpServletResponse.SC_UNAUTHORIZED;
        }
		  
//        Subject currentUser = SecurityUtils.getSubject();
//        UsernamePasswordToken myToken = new UsernamePasswordToken(username, password);
//        try {
//            currentUser.login(myToken);
//            System.out.println(currentUser.isAuthenticated());
//            
//        } catch (UnauthorizedException e) {  
//            e.printStackTrace();
//            return HttpServletResponse.SC_FORBIDDEN;
//        } catch (UnauthenticatedException e) {  
//            e.printStackTrace();
//            return HttpServletResponse.SC_UNAUTHORIZED;
//        } catch (ExcessiveAttemptsException e) {
//            e.printStackTrace();
//            return HttpServletResponse.SC_EXPECTATION_FAILED;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return HttpServletResponse.SC_NOT_FOUND;
//        }
		
		//创建token
		long userId = user.get(0).getId();
		Date expiredDate = DateUtils.addHours(new Date(), expiredHour);
		String[] roles =  new String[]{};
//		String[] roles = new String[user.getRoles().size()];
//		for(int i = 0; i < roles.length; i++) {
//			roles[i] = user.getRoles().get(i).getName();
//		}
		String token = Jwts.builder()
				.setSubject(String.valueOf(userId))
				.setExpiration(expiredDate)
//				.claim("roles", roles)
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
		
//		String avatarUrl = userService.getAvatarUrl(user.getAvatarUrl());
//		String avatarUrl = user.getAvatarUrl();//暂时不使用七牛云

		
		logMsg = "登录成功";
		logger.info(logMsg);
		
		resBody.statusMsg = logMsg;
		resBody.obj1 = new TokenDTO(userId, token, expiredDate);  
		
		return HttpServletResponse.SC_CREATED;
	}
}

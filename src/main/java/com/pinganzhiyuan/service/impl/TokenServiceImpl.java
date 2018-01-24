package com.pinganzhiyuan.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.dto.TokenDTO;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.UserBackendMapper;
import com.pinganzhiyuan.model.UserBackend;
import com.pinganzhiyuan.model.UserBackendExample;
import com.pinganzhiyuan.service.TokenService;
import com.pinganzhiyuan.service.UserService;
import com.pinganzhiyuan.shiro.MyUsernamePasswordToken;
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
	public int create(String username, 
	                  String password, 
	                  String salt,
                	      Integer expiredHour, 
                	      String userAgent, 
                	      ResponseBody resBody,
                	      HttpSession httpSession
                	      ) {
	    UserBackendExample example = new UserBackendExample();
	    example.createCriteria().andUsernameEqualTo(username);
		List<UserBackend> user = userBackendMapper.selectByExample(example);
		
		int statusCode;
		Subject currentUser = SecurityUtils.getSubject();
        // shiro 登录验证
        MyUsernamePasswordToken token = new MyUsernamePasswordToken(username, password);
        token.setSalt(salt);
        try {
            currentUser.login(token);
            System.out.println("currentUser.isAuthenticated(): " + currentUser.isAuthenticated());
            statusCode = HttpServletResponse.SC_CREATED;
        } catch (UnknownAccountException e) {  
            e.printStackTrace();
            logMsg = "用户名和密码不匹配";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_UNAUTHORIZED;
        } catch (IncorrectCredentialsException e) {  
            e.printStackTrace();
            logMsg = "用户名和密码不匹配";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_UNAUTHORIZED;
        } catch (UnauthorizedException e) {  
            e.printStackTrace();
            logMsg = "您没有权限访问";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_FORBIDDEN;
        } catch (UnauthenticatedException e) {  
            e.printStackTrace();
            logMsg = "请登录";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_UNAUTHORIZED;
        } catch (ExcessiveAttemptsException e) {
            e.printStackTrace();
            logMsg = "您的登录次数超过限制";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_EXPECTATION_FAILED;
        } catch (Exception e) {
            e.printStackTrace();
            logMsg = "登录错误";
            resBody.statusMsg = logMsg;
            logger.error(logMsg);
            return HttpServletResponse.SC_NOT_FOUND;
        }
		
//		if (user == null || user.size() == 0) {
//            logMsg = "没有找到这个用户";
//            logger.error(logMsg);
//            
//            resBody.statusMsg = logMsg;
//            resBody.obj1 = null;
//            
////            return HttpServletResponse.SC_NOT_FOUND;
//        } else if (!user.get(0).getPassword().equals(password)) {
//            logMsg = "用户名和密码不匹配";
//            logger.error(logMsg);
//            
//            resBody.statusMsg = logMsg;
//            resBody.obj1 = null;
//            
////            return HttpServletResponse.SC_UNAUTHORIZED;
//        }
		  
		//创建token
		long userId = user.get(0).getId();
		Date expiredDate = DateUtils.addHours(new Date(), expiredHour);
		String[] roles =  new String[]{};
//		String[] roles = new String[user.getRoles().size()];
//		for(int i = 0; i < roles.length; i++) {
//			roles[i] = user.getRoles().get(i).getName();
//		}
		String tokens = Jwts.builder()
				.setSubject(String.valueOf(userId))
				.setExpiration(expiredDate)
//				.claim("roles", roles)
				.compressWith(CompressionCodecs.DEFLATE)
				.signWith(SignatureAlgorithm.HS512, KEY)
				.compact();
		
		logMsg = "登录成功";
		httpSession.setAttribute("user", user);
		logger.info(logMsg);
		resBody.statusMsg = logMsg;
		resBody.obj1 = user;  
		
		return HttpServletResponse.SC_CREATED;
	}
}

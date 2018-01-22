package com.pinganzhiyuan.dto;

import java.util.Date;

/**
* token 传输对象.
* 
* @author x1ny
* @date 2017年5月22日
*/
public class TokenDTO {

	private Long userId;
	private String[] roles;
	private Date expiredDate;
	private String token;

	public TokenDTO() {
		
	}
	
	public TokenDTO(Long userId, String token, Date expiredDate) {
		super();
		this.userId = userId;
		this.token = token;
		this.expiredDate = expiredDate;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
}

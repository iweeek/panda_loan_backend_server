package com.pinganzhiyuan.model;

import java.util.Date;


public class DeviceLog {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.uri
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String uri;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.page_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Integer pageId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.p_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Long pId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.user_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Long userId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.ip
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String ip;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.device_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String deviceId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.user_agent
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String userAgent;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.url_type
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Byte urlType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.sid
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String sid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.is_webview
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Integer isWebview;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.version
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Integer version;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.channel_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Long channelId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.package_name
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String packageName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.longitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Double longitude;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.latitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Double latitude;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.geo_info
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String geoInfo;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.jpush_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private String jpushId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column device_log.created_at
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	private Date createdAt;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.id
	 * @return  the value of device_log.id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.id
	 * @param id  the value for device_log.id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.uri
	 * @return  the value of device_log.uri
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.uri
	 * @param uri  the value for device_log.uri
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.page_id
	 * @return  the value of device_log.page_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Integer getPageId() {
		return pageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.page_id
	 * @param pageId  the value for device_log.page_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.p_id
	 * @return  the value of device_log.p_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Long getpId() {
		return pId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.p_id
	 * @param pId  the value for device_log.p_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setpId(Long pId) {
		this.pId = pId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.user_id
	 * @return  the value of device_log.user_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.user_id
	 * @param userId  the value for device_log.user_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.ip
	 * @return  the value of device_log.ip
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.ip
	 * @param ip  the value for device_log.ip
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.device_id
	 * @return  the value of device_log.device_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.device_id
	 * @param deviceId  the value for device_log.device_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.user_agent
	 * @return  the value of device_log.user_agent
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.user_agent
	 * @param userAgent  the value for device_log.user_agent
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.url_type
	 * @return  the value of device_log.url_type
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Byte getUrlType() {
		return urlType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.url_type
	 * @param urlType  the value for device_log.url_type
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setUrlType(Byte urlType) {
		this.urlType = urlType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.sid
	 * @return  the value of device_log.sid
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getSid() {
		return sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.sid
	 * @param sid  the value for device_log.sid
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setSid(String sid) {
		this.sid = sid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.is_webview
	 * @return  the value of device_log.is_webview
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Integer getIsWebview() {
		return isWebview;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.is_webview
	 * @param isWebview  the value for device_log.is_webview
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setIsWebview(Integer isWebview) {
		this.isWebview = isWebview;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.version
	 * @return  the value of device_log.version
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.version
	 * @param version  the value for device_log.version
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.channel_id
	 * @return  the value of device_log.channel_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Long getChannelId() {
		return channelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.channel_id
	 * @param channelId  the value for device_log.channel_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.package_name
	 * @return  the value of device_log.package_name
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.package_name
	 * @param packageName  the value for device_log.package_name
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.longitude
	 * @return  the value of device_log.longitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.longitude
	 * @param longitude  the value for device_log.longitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.latitude
	 * @return  the value of device_log.latitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.latitude
	 * @param latitude  the value for device_log.latitude
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.geo_info
	 * @return  the value of device_log.geo_info
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getGeoInfo() {
		return geoInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.geo_info
	 * @param geoInfo  the value for device_log.geo_info
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setGeoInfo(String geoInfo) {
		this.geoInfo = geoInfo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.jpush_id
	 * @return  the value of device_log.jpush_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public String getJpushId() {
		return jpushId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.jpush_id
	 * @param jpushId  the value for device_log.jpush_id
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setJpushId(String jpushId) {
		this.jpushId = jpushId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column device_log.created_at
	 * @return  the value of device_log.created_at
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column device_log.created_at
	 * @param createdAt  the value for device_log.created_at
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
   
}
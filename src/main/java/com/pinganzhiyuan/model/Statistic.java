package com.pinganzhiyuan.model;

import java.util.Date;

public class Statistic {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.id
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Long id;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.record_date
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Date recordDate;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.new_device_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer newDeviceCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.new_user_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer newUserCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.device_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer deviceVisitCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.user_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer userVisitCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.product_visit_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer productVisitTotalCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.product_visit_user_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Integer productVisitUserTotalCount;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column statistic.average_product_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	private Double averageProductVisitCount;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.id
	 * @return  the value of statistic.id
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Long getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.id
	 * @param id  the value for statistic.id
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.record_date
	 * @return  the value of statistic.record_date
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Date getRecordDate() {
		return recordDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.record_date
	 * @param recordDate  the value for statistic.record_date
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.new_device_count
	 * @return  the value of statistic.new_device_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getNewDeviceCount() {
		return newDeviceCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.new_device_count
	 * @param newDeviceCount  the value for statistic.new_device_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setNewDeviceCount(Integer newDeviceCount) {
		this.newDeviceCount = newDeviceCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.new_user_count
	 * @return  the value of statistic.new_user_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getNewUserCount() {
		return newUserCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.new_user_count
	 * @param newUserCount  the value for statistic.new_user_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setNewUserCount(Integer newUserCount) {
		this.newUserCount = newUserCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.device_visit_count
	 * @return  the value of statistic.device_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getDeviceVisitCount() {
		return deviceVisitCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.device_visit_count
	 * @param deviceVisitCount  the value for statistic.device_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setDeviceVisitCount(Integer deviceVisitCount) {
		this.deviceVisitCount = deviceVisitCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.user_visit_count
	 * @return  the value of statistic.user_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getUserVisitCount() {
		return userVisitCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.user_visit_count
	 * @param userVisitCount  the value for statistic.user_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setUserVisitCount(Integer userVisitCount) {
		this.userVisitCount = userVisitCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.product_visit_total_count
	 * @return  the value of statistic.product_visit_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getProductVisitTotalCount() {
		return productVisitTotalCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.product_visit_total_count
	 * @param productVisitTotalCount  the value for statistic.product_visit_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setProductVisitTotalCount(Integer productVisitTotalCount) {
		this.productVisitTotalCount = productVisitTotalCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.product_visit_user_total_count
	 * @return  the value of statistic.product_visit_user_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Integer getProductVisitUserTotalCount() {
		return productVisitUserTotalCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.product_visit_user_total_count
	 * @param productVisitUserTotalCount  the value for statistic.product_visit_user_total_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setProductVisitUserTotalCount(Integer productVisitUserTotalCount) {
		this.productVisitUserTotalCount = productVisitUserTotalCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column statistic.average_product_visit_count
	 * @return  the value of statistic.average_product_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public Double getAverageProductVisitCount() {
		return averageProductVisitCount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column statistic.average_product_visit_count
	 * @param averageProductVisitCount  the value for statistic.average_product_visit_count
	 * @mbg.generated  Thu Jan 04 16:38:31 CST 2018
	 */
	public void setAverageProductVisitCount(Double averageProductVisitCount) {
		this.averageProductVisitCount = averageProductVisitCount;
	}
}
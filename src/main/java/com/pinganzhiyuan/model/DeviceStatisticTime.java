package com.pinganzhiyuan.model;

import java.util.Date;

public class DeviceStatisticTime {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column statistic_device_time.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Long id;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column statistic_device_time.statistic_date
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Date statisticDate;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column statistic_device_time.statistic_time
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Date statisticTime;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column statistic_device_time.activate_device_count
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Integer activateDeviceCount;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column statistic_device_time.created_at
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    private Date createdAt;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column statistic_device_time.id
     * @return  the value of statistic_device_time.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column statistic_device_time.id
     * @param id  the value for statistic_device_time.id
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column statistic_device_time.statistic_date
     * @return  the value of statistic_device_time.statistic_date
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Date getStatisticDate() {
        return statisticDate;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column statistic_device_time.statistic_date
     * @param statisticDate  the value for statistic_device_time.statistic_date
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setStatisticDate(Date statisticDate) {
        this.statisticDate = statisticDate;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column statistic_device_time.statistic_time
     * @return  the value of statistic_device_time.statistic_time
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Date getStatisticTime() {
        return statisticTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column statistic_device_time.statistic_time
     * @param statisticTime  the value for statistic_device_time.statistic_time
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setStatisticTime(Date statisticTime) {
        this.statisticTime = statisticTime;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column statistic_device_time.activate_device_count
     * @return  the value of statistic_device_time.activate_device_count
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Integer getActivateDeviceCount() {
        return activateDeviceCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column statistic_device_time.activate_device_count
     * @param activateDeviceCount  the value for statistic_device_time.activate_device_count
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setActivateDeviceCount(Integer activateDeviceCount) {
        this.activateDeviceCount = activateDeviceCount;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column statistic_device_time.created_at
     * @return  the value of statistic_device_time.created_at
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column statistic_device_time.created_at
     * @param createdAt  the value for statistic_device_time.created_at
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
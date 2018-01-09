package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DeviceLogMapper {

    /**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	long countByExample(DeviceLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	int deleteByExample(DeviceLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	@Delete({ "delete from device_log", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	@Insert({ "insert into device_log (uri, page_id, ", "p_id, user_id, ip, ", "device_id, user_agent, ",
			"url_type, sid, is_webview, ", "version, channel_id, ", "package_name, longitude, ", "latitude, geo_info, ",
			"created_at)", "values (#{uri,jdbcType=VARCHAR}, #{pageId,jdbcType=INTEGER}, ",
			"#{pId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT}, #{ip,jdbcType=VARCHAR}, ",
			"#{deviceId,jdbcType=VARCHAR}, #{userAgent,jdbcType=VARCHAR}, ",
			"#{urlType,jdbcType=TINYINT}, #{sid,jdbcType=VARCHAR}, #{isWebview,jdbcType=TINYINT}, ",
			"#{version,jdbcType=INTEGER}, #{channelId,jdbcType=BIGINT}, ",
			"#{packageName,jdbcType=VARCHAR}, #{longitude,jdbcType=DOUBLE}, ",
			"#{latitude,jdbcType=DOUBLE}, #{geoInfo,jdbcType=VARCHAR}, ", "#{createdAt,jdbcType=TIMESTAMP})" })
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
	int insert(DeviceLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	int insertSelective(DeviceLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	List<DeviceLog> selectByExample(DeviceLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	@Select({ "select", "id, uri, page_id, p_id, user_id, ip, device_id, user_agent, url_type, sid, is_webview, ",
			"version, channel_id, package_name, longitude, latitude, geo_info, created_at", "from device_log",
			"where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	DeviceLog selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	int updateByExampleSelective(@Param("record") DeviceLog record, @Param("example") DeviceLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	int updateByExample(@Param("record") DeviceLog record, @Param("example") DeviceLogExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	int updateByPrimaryKeySelective(DeviceLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table device_log
	 * @mbg.generated  Sat Jan 06 00:57:28 CST 2018
	 */
	@Update({ "update device_log", "set uri = #{uri,jdbcType=VARCHAR},", "page_id = #{pageId,jdbcType=INTEGER},",
			"p_id = #{pId,jdbcType=BIGINT},", "user_id = #{userId,jdbcType=BIGINT},", "ip = #{ip,jdbcType=VARCHAR},",
			"device_id = #{deviceId,jdbcType=VARCHAR},", "user_agent = #{userAgent,jdbcType=VARCHAR},",
			"url_type = #{urlType,jdbcType=TINYINT},", "sid = #{sid,jdbcType=VARCHAR},",
			"is_webview = #{isWebview,jdbcType=TINYINT},", "version = #{version,jdbcType=INTEGER},",
			"channel_id = #{channelId,jdbcType=BIGINT},", "package_name = #{packageName,jdbcType=VARCHAR},",
			"longitude = #{longitude,jdbcType=DOUBLE},", "latitude = #{latitude,jdbcType=DOUBLE},",
			"geo_info = #{geoInfo,jdbcType=VARCHAR},", "created_at = #{createdAt,jdbcType=TIMESTAMP}",
			"where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(DeviceLog record);

	@Select({ "select * from device_log where created_at between date(#{startDate}) and date(#{endDate}) group by device_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
    List<DeviceLog> getDeviceLogBetweenDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
//    @Select({ "select * from device_log where created_at < date(#{startDate}) group by device_id" })
//    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
//    List<DeviceLog> getAllGroupByDeviceDataList(@Param("startDate") Date startDate);
    
    @Select({ "select * from device_log where created_at between date(#{startDate}) and date(#{endDate}) group by user_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
    List<DeviceLog> getGroupByUserDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Select({ "\n" + 
            "SELECT * from device_log \n" + 
            "where \n" + 
            "created_at between  date(#{startDate}) and date(#{endDate}) and p_id is not null \n" + 
            "group by user_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
    List<DeviceLog> getAllProductVisitUserDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "created_at between  date(#{startDate}) and date(#{endDate}) and p_id is not null\n" + 
            "group by user_id, p_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
    List<DeviceLog> getAverageUserVisitProductDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "created_at >= #{createdAt,jdbcType=TIMESTAMP} and device_id = #{deviceId,jdbcType=VARCHAR}\n" + 
            "group by device_id, user_id limit 5" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
    List<DeviceLog> getDeviceLogsListByDeviceId(DeviceLog deviceLog);

    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "p_id > 0 and sid is not NULL \n" + 
            "group by user_id,sid" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByUserIdAndSid();
  
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "p_id > 0 and sid is not NULL and created_at between  date(#{startDate}) and date(#{endDate}) \n" + 
            "group by user_id,sid" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByUserIdAndSidAndDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "created_at between date(#{startDate}) and date(#{endDate}) \n" + 
            "group by device_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByDateGroupByDeviceId(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "p_id > 0 and is_webview = 1 and created_at between date(#{startDate}) and date(#{endDate})"})
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByUserIdAndIsWebview(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    //sid is null and  6号的数据(06-07),加上 354，不加409，根据sid获取49，差6条
    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "p_id > 0 and is_webview = 1 and created_at >=  timestamp(#{startDate}) limit 2 "})
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByDate(@Param("startDate") Timestamp startDate);

    
    
    @Select({ "SELECT * from device_log \n" + 
            "where \n" + 
            "created_at between timestamp(#{startTime}) and timestamp(#{endTime}) \n" + 
            "group by device_id" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceLogMapper.BaseResultMap")
	List<DeviceLog> selectByTimeStampDateGroupByDeviceId(@Param("startTime") Timestamp startTime,@Param("endTime") Timestamp endTime);
    
    
    
}
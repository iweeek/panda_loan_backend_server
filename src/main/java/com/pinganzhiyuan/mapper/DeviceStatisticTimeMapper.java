package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.DeviceStatisticTime;
import com.pinganzhiyuan.model.DeviceStatisticTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface DeviceStatisticTimeMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    long countByExample(DeviceStatisticTimeExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    int deleteByExample(DeviceStatisticTimeExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    @Delete({ "delete from statistic_device_time", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    @Insert({ "insert into statistic_device_time (statistic_date, statistic_time, ",
            "activate_device_count, created_at)",
            "values (#{statisticDate,jdbcType=DATE}, #{statisticTime,jdbcType=TIMESTAMP}, ",
            "#{activateDeviceCount,jdbcType=INTEGER}, #{createdAt,jdbcType=TIMESTAMP})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(DeviceStatisticTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    int insertSelective(DeviceStatisticTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    List<DeviceStatisticTime> selectByExample(DeviceStatisticTimeExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    @Select({ "select", "id, statistic_date, statistic_time, activate_device_count, created_at",
            "from statistic_device_time", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.DeviceStatisticTimeMapper.BaseResultMap")
    DeviceStatisticTime selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    int updateByExampleSelective(@Param("record") DeviceStatisticTime record,
            @Param("example") DeviceStatisticTimeExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    int updateByExample(@Param("record") DeviceStatisticTime record,
            @Param("example") DeviceStatisticTimeExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    int updateByPrimaryKeySelective(DeviceStatisticTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_device_time
     * @mbg.generated  Mon Feb 05 09:45:08 CST 2018
     */
    @Update({ "update statistic_device_time", "set statistic_date = #{statisticDate,jdbcType=DATE},",
            "statistic_time = #{statisticTime,jdbcType=TIMESTAMP},",
            "activate_device_count = #{activateDeviceCount,jdbcType=INTEGER},",
            "created_at = #{createdAt,jdbcType=TIMESTAMP}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(DeviceStatisticTime record);
}
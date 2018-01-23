package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.LogInStatisticDaily;
import com.pinganzhiyuan.model.LogInStatisticDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface LogInStatisticDailyMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    long countByExample(LogInStatisticDailyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int deleteByExample(LogInStatisticDailyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Delete({ "delete from statistic_log_in_daily", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Insert({ "insert into statistic_log_in_daily (statistic_date, user_id, ", "device_id, log_in_time, ",
            "created_at)", "values (#{statisticDate,jdbcType=DATE}, #{userId,jdbcType=BIGINT}, ",
            "#{deviceId,jdbcType=BIGINT}, #{logInTime,jdbcType=TIMESTAMP}, ", "#{createdAt,jdbcType=TIMESTAMP})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(LogInStatisticDaily record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int insertSelective(LogInStatisticDaily record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    List<LogInStatisticDaily> selectByExample(LogInStatisticDailyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Select({ "select", "id, statistic_date, user_id, device_id, log_in_time, created_at",
            "from statistic_log_in_daily", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.LogInStatisticDailyMapper.BaseResultMap")
    LogInStatisticDaily selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExampleSelective(@Param("record") LogInStatisticDaily record,
            @Param("example") LogInStatisticDailyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExample(@Param("record") LogInStatisticDaily record,
            @Param("example") LogInStatisticDailyExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByPrimaryKeySelective(LogInStatisticDaily record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table statistic_log_in_daily
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Update({ "update statistic_log_in_daily", "set statistic_date = #{statisticDate,jdbcType=DATE},",
            "user_id = #{userId,jdbcType=BIGINT},", "device_id = #{deviceId,jdbcType=BIGINT},",
            "log_in_time = #{logInTime,jdbcType=TIMESTAMP},", "created_at = #{createdAt,jdbcType=TIMESTAMP}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(LogInStatisticDaily record);
}
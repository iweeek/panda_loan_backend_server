package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    long countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    @Delete({ "delete from user", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    @Insert({ "insert into user (username, password, ", "regist_time)",
            "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ", "#{registTime,jdbcType=BIGINT})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    @Select({ "select", "id, username, password, regist_time", "from user", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.UserMapper.BaseResultMap")
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user
     * @mbg.generated  Fri Dec 29 00:06:00 CST 2017
     */
    @Update({ "update user", "set username = #{username,jdbcType=VARCHAR},", "password = #{password,jdbcType=VARCHAR},",
            "regist_time = #{registTime,jdbcType=BIGINT}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(User record);

    @Select({ "select u.username, u.regist_time, c.name, c.identity_no, c.residence_addr, c.is_man,\n" + 
            "c.nation, c.birthday, c.identity_auth, c.identity_expiration, c.education, c.guarantee, c.profession from (\n" + 
            "select * from device_log where created_at between date(#{startDate}) and date(#{endDate}) group by user_id\n" + 
            ") as d \n" + 
            "join user as u on d.user_id = u.id\n" + 
            "join client as c on c.user_id = u.id" })
    @ResultMap("com.pinganzhiyuan.mapper.UserMapper.BaseResultMap")
    List<User> getExisitUserDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    @Select({"SELECT * FROM user as u join client as c on u.id = c.user_id "
            + "where date(from_unixtime(regist_time / 1000)) between date(#{startDate}) and date(#{endDate})"})
    @ResultMap("com.pinganzhiyuan.mapper.UserMapper.BaseResultMap")
    List<User> gitNewUserDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    
    @Select({"SELECT * FROM user where date(from_unixtime(regist_time / 1000)) < date(#{startDate}) "})
    @ResultMap("com.pinganzhiyuan.mapper.UserMapper.BaseResultMap")
    List<User> gitNewUserBeforeDateDataList(@Param("startDate") Date startDate);
    
    
    @Select({"select d.user_id  as id, u.username, u.regist_time, c.name, c.identity_no, c.residence_addr, c.is_man,\n" + 
            "c.nation, c.birthday, c.identity_auth, c.identity_expiration, c.education, c.guarantee, c.profession\n" + 
            "from (\n" + 
            "select * from device_log where created_at between date(#{startDate}) and date(#{endDate}) group by user_id\n" + 
            ") as d \n" + 
            "left join user as u on d.user_id = u.id\n" + 
            "left join client as c on c.user_id = u.id"})
    @ResultMap("com.pinganzhiyuan.mapper.UserMapper.BaseResultMap")
    List<User> getUserVisitRecordDataList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
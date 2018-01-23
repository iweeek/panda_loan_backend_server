package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.UserBackend;
import com.pinganzhiyuan.model.UserBackendExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface UserBackendMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    long countByExample(UserBackendExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int deleteByExample(UserBackendExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Delete({ "delete from user_backend", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Insert({ "insert into user_backend (username, password, ", "role_ids)",
            "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ", "#{roleIds,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(UserBackend record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int insertSelective(UserBackend record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    List<UserBackend> selectByExample(UserBackendExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Select({ "select", "id, username, password, role_ids", "from user_backend", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.UserBackendMapper.BaseResultMap")
    UserBackend selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExampleSelective(@Param("record") UserBackend record, @Param("example") UserBackendExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExample(@Param("record") UserBackend record, @Param("example") UserBackendExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByPrimaryKeySelective(UserBackend record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table user_backend
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Update({ "update user_backend", "set username = #{username,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},", "role_ids = #{roleIds,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(UserBackend record);
}
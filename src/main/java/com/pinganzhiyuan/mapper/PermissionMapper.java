package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.Permission;
import com.pinganzhiyuan.model.PermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface PermissionMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    long countByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    int deleteByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    @Delete({ "delete from permission", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    @Insert({ "insert into permission (name, type, ", "url, parent_id, parent_ids, ", "permission, is_enabled)",
            "values (#{name,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
            "#{url,jdbcType=VARCHAR}, #{parentId,jdbcType=BIGINT}, #{parentIds,jdbcType=VARCHAR}, ",
            "#{permission,jdbcType=VARCHAR}, #{isEnabled,jdbcType=BIT})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    @Select({ "select", "id, name, type, url, parent_id, parent_ids, permission, is_enabled", "from permission",
            "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.PermissionMapper.BaseResultMap")
    Permission selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table permission
     * @mbg.generated  Mon Jan 15 11:33:27 CST 2018
     */
    @Update({ "update permission", "set name = #{name,jdbcType=VARCHAR},", "type = #{type,jdbcType=VARCHAR},",
            "url = #{url,jdbcType=VARCHAR},", "parent_id = #{parentId,jdbcType=BIGINT},",
            "parent_ids = #{parentIds,jdbcType=VARCHAR},", "permission = #{permission,jdbcType=VARCHAR},",
            "is_enabled = #{isEnabled,jdbcType=BIT}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(Permission record);
}
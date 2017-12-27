package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.TopNav;
import com.pinganzhiyuan.model.TopNavExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TopNavMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    long countByExample(TopNavExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int deleteByExample(TopNavExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Delete({ "delete from top_nav", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Insert({ "insert into top_nav (title, subtitle, ", "icon_url, url)",
            "values (#{title,jdbcType=VARCHAR}, #{subtitle,jdbcType=VARCHAR}, ",
            "#{iconUrl,jdbcType=VARCHAR}, #{url,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(TopNav record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int insertSelective(TopNav record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    List<TopNav> selectByExample(TopNavExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Select({ "select", "id, title, subtitle, icon_url, url", "from top_nav", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.TopNavMapper.BaseResultMap")
    TopNav selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByExampleSelective(@Param("record") TopNav record, @Param("example") TopNavExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByExample(@Param("record") TopNav record, @Param("example") TopNavExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByPrimaryKeySelective(TopNav record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table top_nav
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Update({ "update top_nav", "set title = #{title,jdbcType=VARCHAR},", "subtitle = #{subtitle,jdbcType=VARCHAR},",
            "icon_url = #{iconUrl,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(TopNav record);
}
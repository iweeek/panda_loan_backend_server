package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.Banner;
import com.pinganzhiyuan.model.BannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface BannerMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    long countByExample(BannerExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int deleteByExample(BannerExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Delete({ "delete from banner", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Insert({ "insert into banner (credit_ceiling, unit, ", "url, pass_rate)",
            "values (#{creditCeiling,jdbcType=INTEGER}, #{unit,jdbcType=VARCHAR}, ",
            "#{url,jdbcType=VARCHAR}, #{passRate,jdbcType=DOUBLE})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Banner record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int insertSelective(Banner record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    List<Banner> selectByExample(BannerExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Select({ "select", "id, credit_ceiling, unit, url, pass_rate", "from banner", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.BannerMapper.BaseResultMap")
    Banner selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") Banner record, @Param("example") BannerExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByExample(@Param("record") Banner record, @Param("example") BannerExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(Banner record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Update({ "update banner", "set credit_ceiling = #{creditCeiling,jdbcType=INTEGER},",
            "unit = #{unit,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR},",
            "pass_rate = #{passRate,jdbcType=DOUBLE}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(Banner record);
}
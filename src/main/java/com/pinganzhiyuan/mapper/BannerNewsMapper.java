package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.BannerNews;
import com.pinganzhiyuan.model.BannerNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface BannerNewsMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    long countByExample(BannerNewsExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int deleteByExample(BannerNewsExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Delete({ "delete from banner_news", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Insert({ "insert into banner_news (description, product_name, ", "url)",
            "values (#{description,jdbcType=VARCHAR}, #{productName,jdbcType=VARCHAR}, ", "#{url,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(BannerNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int insertSelective(BannerNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    List<BannerNews> selectByExample(BannerNewsExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Select({ "select", "id, description, product_name, url", "from banner_news", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.BannerNewsMapper.BaseResultMap")
    BannerNews selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByExampleSelective(@Param("record") BannerNews record, @Param("example") BannerNewsExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByExample(@Param("record") BannerNews record, @Param("example") BannerNewsExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    int updateByPrimaryKeySelective(BannerNews record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table banner_news
     * @mbg.generated  Tue Feb 06 17:28:36 CST 2018
     */
    @Update({ "update banner_news", "set description = #{description,jdbcType=VARCHAR},",
            "product_name = #{productName,jdbcType=VARCHAR},", "url = #{url,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(BannerNews record);
}
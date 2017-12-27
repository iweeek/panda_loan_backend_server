package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.Term;
import com.pinganzhiyuan.model.TermExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface TermMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    long countByExample(TermExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int deleteByExample(TermExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Delete({ "delete from term", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Insert({ "insert into term (duration, sequency)",
            "values (#{duration,jdbcType=VARCHAR}, #{sequency,jdbcType=INTEGER})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Term record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int insertSelective(Term record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    List<Term> selectByExample(TermExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Select({ "select", "id, duration, sequency", "from term", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.TermMapper.BaseResultMap")
    Term selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByExampleSelective(@Param("record") Term record, @Param("example") TermExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByExample(@Param("record") Term record, @Param("example") TermExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    int updateByPrimaryKeySelective(Term record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table term
     * @mbg.generated  Tue Dec 26 15:59:52 CST 2017
     */
    @Update({ "update term", "set duration = #{duration,jdbcType=VARCHAR},", "sequency = #{sequency,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(Term record);
}
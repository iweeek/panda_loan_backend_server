package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.GuaranteeTypeMapping;
import com.pinganzhiyuan.model.GuaranteeTypeMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface GuaranteeTypeMappingMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	long countByExample(GuaranteeTypeMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int deleteByExample(GuaranteeTypeMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Delete({ "delete from guarantee_type_mapping", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Insert({ "insert into guarantee_type_mapping (guarantee_id, product_type_id)",
			"values (#{guaranteeId,jdbcType=BIGINT}, #{productTypeId,jdbcType=BIGINT})" })
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
	int insert(GuaranteeTypeMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int insertSelective(GuaranteeTypeMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	List<GuaranteeTypeMapping> selectByExample(GuaranteeTypeMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Select({ "select", "id, guarantee_id, product_type_id", "from guarantee_type_mapping",
			"where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("com.pinganzhiyuan.mapper.GuaranteeTypeMappingMapper.BaseResultMap")
	GuaranteeTypeMapping selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByExampleSelective(@Param("record") GuaranteeTypeMapping record,
			@Param("example") GuaranteeTypeMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByExample(@Param("record") GuaranteeTypeMapping record,
			@Param("example") GuaranteeTypeMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByPrimaryKeySelective(GuaranteeTypeMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table guarantee_type_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Update({ "update guarantee_type_mapping", "set guarantee_id = #{guaranteeId,jdbcType=BIGINT},",
			"product_type_id = #{productTypeId,jdbcType=BIGINT}", "where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(GuaranteeTypeMapping record);
}
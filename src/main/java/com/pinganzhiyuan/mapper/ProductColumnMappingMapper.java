package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.ProductColumnMapping;
import com.pinganzhiyuan.model.ProductColumnMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ProductColumnMappingMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	long countByExample(ProductColumnMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int deleteByExample(ProductColumnMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Delete({ "delete from product_column_mapping", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Insert({ "insert into product_column_mapping (column_key, product_id)",
			"values (#{columnKey,jdbcType=VARCHAR}, #{productId,jdbcType=BIGINT})" })
	@SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
	int insert(ProductColumnMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int insertSelective(ProductColumnMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	List<ProductColumnMapping> selectByExample(ProductColumnMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Select({ "select", "id, column_key, product_id", "from product_column_mapping",
			"where id = #{id,jdbcType=BIGINT}" })
	@ResultMap("com.pinganzhiyuan.mapper.ProductColumnMappingMapper.BaseResultMap")
	ProductColumnMapping selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByExampleSelective(@Param("record") ProductColumnMapping record,
			@Param("example") ProductColumnMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByExample(@Param("record") ProductColumnMapping record,
			@Param("example") ProductColumnMappingExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	int updateByPrimaryKeySelective(ProductColumnMapping record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table product_column_mapping
	 * @mbg.generated  Wed Mar 07 15:51:36 CST 2018
	 */
	@Update({ "update product_column_mapping", "set column_key = #{columnKey,jdbcType=VARCHAR},",
			"product_id = #{productId,jdbcType=BIGINT}", "where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(ProductColumnMapping record);
}
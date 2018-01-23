package com.pinganzhiyuan.mapper;

import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.ChannelExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

public interface ChannelMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    long countByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int deleteByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Delete({ "delete from channel", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Insert({ "insert into channel (name, channel_name)",
            "values (#{name,jdbcType=VARCHAR}, #{channelName,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insert(Channel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int insertSelective(Channel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    List<Channel> selectByExample(ChannelExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Select({ "select", "id, name, channel_name", "from channel", "where id = #{id,jdbcType=BIGINT}" })
    @ResultMap("com.pinganzhiyuan.mapper.ChannelMapper.BaseResultMap")
    Channel selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExampleSelective(@Param("record") Channel record, @Param("example") ChannelExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByExample(@Param("record") Channel record, @Param("example") ChannelExample example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    int updateByPrimaryKeySelective(Channel record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table channel
     * @mbg.generated  Tue Jan 23 10:34:09 CST 2018
     */
    @Update({ "update channel", "set name = #{name,jdbcType=VARCHAR},",
            "channel_name = #{channelName,jdbcType=VARCHAR}", "where id = #{id,jdbcType=BIGINT}" })
    int updateByPrimaryKey(Channel record);
}
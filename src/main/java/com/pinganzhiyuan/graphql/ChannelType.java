package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.BannerNewsMapper;
import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.ChannelExample;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

/**
 * 发布应用市场所对应的渠道
 * @author nijun
 *
 */
@Component
public class ChannelType {
    
    private static GraphQLFieldDefinition listQueryField;
    private static ChannelMapper channelMapper;
    private static GraphQLObjectType type;
   
    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Channel").description("Channel")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("name")
                            .description("渠道名字")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelName")
                            .description("渠道代号")
                            .type(Scalars.GraphQLString)
                            .build())
                    .build();
        }
        return type;
    }

    public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("Channels")
                    .description("渠道列表")
                    .type(new GraphQLList(getType()))
                    .dataFetcher(environment ->  {
                        ChannelExample example = new ChannelExample();
                        List<Channel> list = channelMapper.selectByExample(example);
                        return list;
                    } ).build();
        }
        return listQueryField;
    }
    
    @Autowired(required = true)
	public void setChannelMapper(ChannelMapper channelMapper) {
		ChannelType.channelMapper = channelMapper;
	}
}

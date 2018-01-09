package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.UserPortrayalMapper;
import com.pinganzhiyuan.model.DeviceStatisticTime;
import com.pinganzhiyuan.model.UserPortrayal;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class UserPortrayalType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	private static UserPortrayalMapper userPortrayalMapper;
	
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("UserPortrayal").description("用户画像表")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("cellPhone")
                            .description("手机号")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("registTime")
                            .description("注册日期")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            	UserPortrayal userPortrayal = environment.getSource();
								return sdf.format(userPortrayal.getRegistTime());
                            	} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isApprove")
                            .description("认证状态")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("approveTime")
                            .description("认证日期")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	UserPortrayal userPortrayal = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								return sdf.format(userPortrayal.getApproveTime());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("birthDate")
                            .description("出生日期")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	UserPortrayal userPortrayal = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								return sdf.format(userPortrayal.getBirthDate());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isMan")
                            .description("性别")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("prefixOfIdentity")
                            .description("身份证前缀")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("education")
                            .description("学历")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("profession")
                            .description("社会身份")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("guarantee")
                            .description("信用类别")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("createdAt")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	UserPortrayal userPortrayal = environment.getSource();
                            	DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(userPortrayal.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
	}
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("userPortrayal")
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("设备统计信息")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("UserPortrayalPage").description("用户画像详情分页").build())
					.dataFetcher(environment ->  {
	                	PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return userPortrayalMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }

	@Autowired(required = true)
	public void setUserPortrayalMapper(UserPortrayalMapper userPortrayalMapper) {
		UserPortrayalType.userPortrayalMapper = userPortrayalMapper;
	}
	
	
}

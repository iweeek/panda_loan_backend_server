package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.LogInStatisticDailyMapper;
import com.pinganzhiyuan.model.LogInStatisticDaily;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class LogInType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	
	private static LogInStatisticDailyMapper logInStatisticDailyMapper;
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("LogIn").description("LogIn")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("statisticDate")
                            .description("统计日期")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
								LogInStatisticDaily loginInStatisticDaily = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								return sdf.format(loginInStatisticDaily.getStatisticDate());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户Id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceId")
                            .description("设备Id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("logInTime")
                            .description("登录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
								LogInStatisticDaily loginInStatisticDaily = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(loginInStatisticDaily.getLogInTime());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("createdAt")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
								LogInStatisticDaily loginInStatisticDaily = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(loginInStatisticDaily.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
    }
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("logInStatisticDaily")
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("登录信息")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("LogInPage").description("登录信息分页").build())
					.dataFetcher(environment ->  {
	                	PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return logInStatisticDailyMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }

	@Autowired(required = true)
	public void setLogInStatisticDailyMapper(LogInStatisticDailyMapper logInStatisticDailyMapper) {
		LogInType.logInStatisticDailyMapper = logInStatisticDailyMapper;
	}
	
	
}

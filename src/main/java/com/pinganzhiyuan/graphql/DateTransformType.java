package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.StatisticDateTransformMapper;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.StatisticDateTransform;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class DateTransformType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	
	private static StatisticDateTransformMapper statisticDateTransformMapper;
	
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("DateTransform").description("流量转化详情")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceId")
                            .description("设备主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productId")
                            .description("产品主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productName")
                            .description("产品名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productUrl")
                            .description("产品URL")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("startVisitTime")
                            .description("访问开始时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	StatisticDateTransform statisticDateTransform = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(statisticDateTransform.getStartVisitTime());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("endVisitTime")
                            .description("访问结束时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	StatisticDateTransform statisticDateTransform = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(statisticDateTransform.getStartVisitTime());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("stayTime")
                            .description("停留时长")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isRequestCode")
                            .description("是否请求验证码")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("acquireCodeTime")
                            .description("请求验证码时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	StatisticDateTransform statisticDateTransform = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(statisticDateTransform.getAcquireCodeTime());
							 } )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isRequestRegister")
                            .description("是否请求注册")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("submitRegisterTime")
                            .description("提交注册时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	StatisticDateTransform statisticDateTransform = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(statisticDateTransform.getSubmitRegisterTime());
                            } )
							.build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("createdAt")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	StatisticDateTransform statisticDateTransform = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(statisticDateTransform.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
    }
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("dateTransform")
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("流量转化记录")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("DateTransformPage").description("流量转化记录分页").build())
					.dataFetcher(environment ->  {
	                	PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return statisticDateTransformMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }
	
	@Autowired(required = true)
	public void setStatisticDateTransformMapper(StatisticDateTransformMapper statisticDateTransformMapper) {
		DateTransformType.statisticDateTransformMapper = statisticDateTransformMapper;
	}
	
	
	
}

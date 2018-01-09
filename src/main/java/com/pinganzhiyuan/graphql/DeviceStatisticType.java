package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.DeviceStatisticDailyMapper;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.DeviceStatisticDaily;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class DeviceStatisticType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	
	private static DeviceStatisticDailyMapper deviceStatisticDailyMapper;
	
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("DeviceStatisticDaily").description("DeviceStatisticDaily")
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
                            	DeviceStatisticDaily deviceStatisticDaily = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								return sdf.format(deviceStatisticDaily.getStatisticDate());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelId")
                            .description("渠道id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelName")
                            .description("渠道名称")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("appProductId")
                            .description("产品id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("appProductName")
                            .description("产品名称")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("activateDeviceCount")
                            .description("激活设备数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("createdAt")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	DeviceStatisticDaily deviceStatisticDaily = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(deviceStatisticDaily.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
	}
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("statisticDevice")
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("设备统计信息")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("StatisticDevicePage").description("设备统计信息分页").build())
					.dataFetcher(environment ->  {
	                	PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return deviceStatisticDailyMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }

    @Autowired(required = true)
	public void setDeviceStatisticDailyMapper(DeviceStatisticDailyMapper deviceStatisticDailyMapper) {
		DeviceStatisticType.deviceStatisticDailyMapper = deviceStatisticDailyMapper;
	}
	

    
}

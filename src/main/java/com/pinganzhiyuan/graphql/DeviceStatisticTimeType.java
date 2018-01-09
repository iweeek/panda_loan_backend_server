package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.DeviceStatisticTimeMapper;
import com.pinganzhiyuan.model.DeviceStatisticTime;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class DeviceStatisticTimeType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	private static DeviceStatisticTimeMapper deviceStatisticTimeMapper;
	
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("DeviceStatisticTime").description("DeviceStatisticTime")
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
                            	DeviceStatisticTime deviceStatisticTime = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
								return sdf.format(deviceStatisticTime.getStatisticDate());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("statisticTime")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	DeviceStatisticTime deviceStatisticTime = environment.getSource();
                            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        		Date startDate = deviceStatisticTime.getStatisticTime();
                                Calendar c = Calendar.getInstance();
                                c.setTime(startDate);
                                c.add(Calendar.HOUR, 2);// +2小时
                                Date endDate = c.getTime();
								String date = sdf.format(startDate) + " - " + sdf.format(endDate);
								return date;
                            	} )
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
                            	DeviceStatisticTime deviceStatisticTime = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(deviceStatisticTime.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
	}
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("statisticDeviceTime")
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("设备统计信息")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("StatisticDeviceTimePage").description("日增设备时间分布分页").build())
					.dataFetcher(environment ->  {
	                	PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return deviceStatisticTimeMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }
	
	@Autowired(required = true)
	public void setDeviceStatisticTimeMapper(DeviceStatisticTimeMapper deviceStatisticTimeMapper) {
		DeviceStatisticTimeType.deviceStatisticTimeMapper = deviceStatisticTimeMapper;
	}
}

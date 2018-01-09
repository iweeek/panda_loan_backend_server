package com.pinganzhiyuan.graphql;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.DeviceStatisticDailyMapper;
import com.pinganzhiyuan.mapper.DeviceStatisticTimeMapper;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.DeviceExample;
import com.pinganzhiyuan.model.DeviceExample.Criteria;
import com.pinganzhiyuan.model.DeviceStatisticDaily;
import com.pinganzhiyuan.model.DeviceStatisticTime;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
@Component
public class DeviceType {
	private static GraphQLFieldDefinition listQueryField;
	private static GraphQLObjectType type;
	private static DeviceMapper deviceMapper;
	private static DeviceStatisticDailyMapper deviceStatisticDailyMapper;
	private static DeviceStatisticTimeMapper deviceStatisticTimeMapper;
	
	public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Device").description("Device")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceId")
                            .description("设备生成唯一标识")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceBrand")
                            .description("设备品牌")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceSystemVersion")
                            .description("设备系统版本")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("maidenStartTime")
                            .description("第一次使用时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
								Device device = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(device.getMaidenStartTime());
							} )
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("maidenStartIp")
                            .description("第一次使用的ip")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("maidenStartLongitude")
                            .description("第一次使用的经度")
                            .type(Scalars.GraphQLBigDecimal)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("maidenStartLatitude")
                            .description("第一次使用的纬度")
                            .type(Scalars.GraphQLBigDecimal)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelId")
                            .description("渠道id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelName")
                            .description("渠道名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("appProductId")
                            .description("APP产品id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("appProductName")
                            .description("APP产品名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("appProductVersion")
                            .description("APP版本号")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isRoot")
                            .description("是否越狱/ROOT")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("createdAt")
                            .description("记录时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                            	Device device = environment.getSource();
								DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
								return sdf.format(device.getCreatedAt());
							} )
                            .build())
                    .build();
        }
        return type;
    }
	
	public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("device")
                    .argument(GraphQLArgument.newArgument().name("dailyId").type(Scalars.GraphQLLong).build())
                    .argument(GraphQLArgument.newArgument().name("timeId").type(Scalars.GraphQLLong).build())
					.argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
					.argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
					.description("设备登记信息")
                    .type(PageType.getPageTypeBuidler(getType())
							.name("DevicePage").description("设备登记信息分页").build())
					.dataFetcher(environment ->  {
						DeviceExample deviceExample = new DeviceExample();
						Criteria criteria = deviceExample.createCriteria();
	                	Long dailyId = environment.getArgument("dailyId");
	                	if (dailyId != null) {
	                		DeviceStatisticDaily deviceStatisticDaily = deviceStatisticDailyMapper.selectByPrimaryKey(dailyId);
	                		Date startDate = deviceStatisticDaily.getStatisticDate();
	                		Calendar c = Calendar.getInstance();
	                        c.setTime(startDate);
	                        c.add(Calendar.DAY_OF_MONTH, 1);// +1天
	                        Date endDate = c.getTime();
	                		criteria.andMaidenStartTimeBetween(startDate, endDate);
	                		criteria.andChannelIdEqualTo(deviceStatisticDaily.getChannelId());
	                		criteria.andAppProductIdEqualTo(deviceStatisticDaily.getAppProductId());
	                	}
	                	Long timeId = environment.getArgument("timeId");
	                	if (timeId != null) {
	                		DeviceStatisticTime deviceStatisticTime = deviceStatisticTimeMapper.selectByPrimaryKey(timeId);
	                		Date startDate = deviceStatisticTime.getStatisticTime();
	                		Calendar c = Calendar.getInstance();
	                        c.setTime(startDate);
	                        c.add(Calendar.HOUR, 2);// +2小时
	                        Date endDate = c.getTime();
	                		criteria.andMaidenStartTimeBetween(startDate, endDate);
	                	}
						PageHelper.startPage(environment.getArgument("pageNumber"), environment.getArgument("pageSize"));
                        return deviceMapper.selectByExample(deviceExample);
                    } ).build();
        }
        return listQueryField;
    }
	
    @Autowired(required = true)
	public void setDeviceMapper(DeviceMapper deviceMapper) {
		DeviceType.deviceMapper = deviceMapper;
	}

    @Autowired(required = true)
	public void setDeviceStatisticDailyMapper(DeviceStatisticDailyMapper deviceStatisticDailyMapper) {
		DeviceType.deviceStatisticDailyMapper = deviceStatisticDailyMapper;
	}

    @Autowired(required = true)
	public void setDeviceStatisticTimeMapper(DeviceStatisticTimeMapper deviceStatisticTimeMapper) {
		DeviceType.deviceStatisticTimeMapper = deviceStatisticTimeMapper;
	}
	
    
	
}

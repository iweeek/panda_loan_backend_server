package com.pinganzhiyuan.graphql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.DeviceMapper;
import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Device;
import com.pinganzhiyuan.model.DeviceExample;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.Statistic;
import com.pinganzhiyuan.model.User;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class DeviceType {
//    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition newDeviceListQueryField;
//    private static GraphQLFieldDefinition newUserListQueryField;
//    private static GraphQLFieldDefinition userVisitListQueryField;
    private static GraphQLFieldDefinition deviceVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitUserListQueryField;
    
    private static DeviceMapper deviceMapper;
    private static DeviceLogMapper deviceLogMapper;
    private static UserMapper userMapper;
    private static StatisticMapper statisticMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Device").description("统计")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("uri")
//                            .description("请求路径")
//                            .type(Scalars.GraphQLString)
//                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("pageId")
//                            .description("页码ID")
//                            .type(Scalars.GraphQLLong)
//                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("pId")
//                            .description("产品ID")
//                            .type(Scalars.GraphQLLong)
//                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户ID")
                            .type(Scalars.GraphQLLong)
                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("ip")
//                            .description("ip")
//                            .type(Scalars.GraphQLString)
//                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceId")
                            .description("设备Id")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userAgent")
                            .description("userAgent")
                            .type(Scalars.GraphQLString)
                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("urlType")
//                            .description("urlType")
//                            .type(Scalars.GraphQLString)
//                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("isWebview")
//                            .description("isWebview")
//                            .type(Scalars.GraphQLBoolean)
//                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("version")
                            .description("version")
                            .type(Scalars.GraphQLInt)
                            .build())  
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("channelId")
                            .description("channelId")
                            .type(Scalars.GraphQLLong)
                            .build())  
                    .field(GraphQLFieldDefinition
                          .newFieldDefinition().name("recordDate")
                          .description("记录时间")
                          .type(Scalars.GraphQLString)
                          .dataFetcher(environment ->  {
                              Device source = environment.getSource();
                              DateTime dateTime = new DateTime(source.getRecordDate());
                              String date = dateTime.toString("yyyy-MM-dd");
                              return date;
                          })
                          .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("packageName")
//                            .description("packageName")
//                            .type(Scalars.GraphQLString)
//                            .build())  
//                    .field(GraphQLFieldDefinition.newFieldDefinition()
//                            .name("longitude")
//                            .description("该时刻的经度")
//                            .type(Scalars.GraphQLFloat)
//                            .build())
//                    .field(GraphQLFieldDefinition.newFieldDefinition()
//                            .name("latitude")
//                            .description("该时刻的纬度")
//                            .type(Scalars.GraphQLFloat)
//                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("geoInfo")
//                            .description("geoInfo")
//                            .type(Scalars.GraphQLString)
//                            .build())  

                    .build();
        }
        return type;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
//                    .name("statistics")
//                    .description("埋点统计列表")
//                    .type(PageType.getPageTypeBuidler(getType()).name("StatisticPage").description("埋点统计类型分页").build())
//                    .dataFetcher(environment ->  {
//                        PageHelper.startPage(environment.getArgument("pageNumber"),
//                                environment.getArgument("pageSize"));
//                        List<Statistic> list = statisticMapper.selectByExample(null);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    /**
     * 已完成
     * @return
     */
    public static GraphQLFieldDefinition getNewDeviceListQueryField() {
        if(newDeviceListQueryField == null) {
            newDeviceListQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("newDeviceStatistics")
                    .description("埋点统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("DevicePage").description("埋点统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        Long id = environment.getArgument("id");
                        Statistic st = statisticMapper.selectByPrimaryKey(id);
                        Date startDate = st.getRecordDate();
                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
                        DeviceExample example = new DeviceExample();
                        example.createCriteria().andRecordDateBetween(startDate, endDate);
                        List<Device> list = deviceMapper.selectByExample(example);
                        return list;
                    } ).build();
        }
        return newDeviceListQueryField;
    }
   
    
   
//    
//    public static GraphQLFieldDefinition getTotalProductVisitListQueryField() {
//        if(totalProductVisitListQueryField == null) {
//            totalProductVisitListQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
//                    .name("totalProductVisitStatistics")
//                    .description("埋点统计列表")
//                    .type(PageType.getPageTypeBuidler(getType()).name("totalProductVisitStatisticPage").description("埋点统计类型分页").build())
//                    .dataFetcher(environment ->  {
//                        PageHelper.startPage(environment.getArgument("pageNumber"),
//                                environment.getArgument("pageSize"));
//                        Long id = environment.getArgument("id");
//                        Statistic st = statisticMapper.selectByPrimaryKey(id);
//                        Date startDate = st.getRecordDate();
//                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
//                        
//                        DeviceLogExample example = new DeviceLogExample();
//                        example.createCriteria().andCreatedAtBetween(startDate, endDate).andPIdIsNotNull();
//                        List<DeviceLog> list = deviceMapper.selectByExample(example);
//                        return list;
//                    } ).build();
//        }
//        return totalProductVisitListQueryField;
//    }
//    
//    public static GraphQLFieldDefinition getTotalProductVisitUserListQueryField() {
//        if(totalProductVisitUserListQueryField == null) {
//            totalProductVisitUserListQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
//                    .name("totalProductUserVisitStatistics")
//                    .description("埋点统计列表")
//                    .type(PageType.getPageTypeBuidler(getType()).name("totalProductUserVisitStatisticPage").description("埋点统计类型分页").build())
//                    .dataFetcher(environment ->  {
//                        PageHelper.startPage(environment.getArgument("pageNumber"),
//                                environment.getArgument("pageSize"));
//                        Long id = environment.getArgument("id");
//                        Statistic st = statisticMapper.selectByPrimaryKey(id);
//                        Date startDate = st.getRecordDate();
//                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
//                        List<Device> list = deviceMapper.getAllProductVisitUserDataList(startDate, endDate);
//                        return list;
//                    } ).build();
//        }
//        return totalProductVisitUserListQueryField;
//    }
    
    @Autowired(required = true)
    public void setDeviceMapper(DeviceMapper deviceMapper) {
        DeviceType.deviceMapper = deviceMapper;
    }

    @Autowired(required = true)
    public void setStatisticMapper(StatisticMapper statisticMapper) {
        DeviceType.statisticMapper = statisticMapper;
    }

    @Autowired(required = true)
    public  void setUserMapper(UserMapper userMapper) {
        DeviceType.userMapper = userMapper;
    }

    @Autowired(required = true)
    public  void setDeviceLogMapper(DeviceLogMapper deviceLogMapper) {
        DeviceType.deviceLogMapper = deviceLogMapper;
    }
    
}

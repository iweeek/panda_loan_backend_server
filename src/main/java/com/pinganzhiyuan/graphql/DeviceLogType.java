package com.pinganzhiyuan.graphql;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.ProductStatisticMapper;
import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.model.DeviceLog;
import com.pinganzhiyuan.model.DeviceLogExample;
import com.pinganzhiyuan.model.ProductStatistic;
import com.pinganzhiyuan.model.Statistic;
import com.pinganzhiyuan.model.User;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class DeviceLogType {
    private static GraphQLFieldDefinition productDeviceLogQueryField;
//    private static GraphQLFieldDefinition newDeviceListQueryField;
//    private static GraphQLFieldDefinition newUserListQueryField;
//    private static GraphQLFieldDefinition userVisitListQueryField;
    private static GraphQLFieldDefinition deviceVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitUserListQueryField;
    
    private static DeviceLogMapper deviceLogMapper;
    private static StatisticMapper statisticMapper;
    private static ProductStatisticMapper productStatisticMapper;
    
    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("DeviceLog").description("统计")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("uri")
                            .description("请求路径")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("pageId")
                            .description("页码ID")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("pId")
                            .description("产品ID")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户ID")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("ip")
                            .description("ip")
                            .type(Scalars.GraphQLString)
                            .build())
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
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("urlType")
                            .description("urlType")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isWebview")
                            .description("isWebview")
                            .type(Scalars.GraphQLBoolean)
                            .build())
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
                            .newFieldDefinition().name("packageName")
                            .description("packageName")
                            .type(Scalars.GraphQLString)
                            .build())  
                    .field(GraphQLFieldDefinition.newFieldDefinition()
                            .name("longitude")
                            .description("该时刻的经度")
                            .type(Scalars.GraphQLFloat)
                            .build())
                    .field(GraphQLFieldDefinition.newFieldDefinition()
                            .name("latitude")
                            .description("该时刻的纬度")
                            .type(Scalars.GraphQLFloat)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("geoInfo")
                            .description("geoInfo")
                            .type(Scalars.GraphQLString)
                            .build())  
                    .field(GraphQLFieldDefinition
                                    .newFieldDefinition().name("createdAt")
                                    .description("创建时间")
                                    .type(Scalars.GraphQLString)
                                    .dataFetcher(environment ->  {
                                        Statistic source = environment.getSource();
                                        DateTime dateTime = new DateTime(source.getRecordDate());
                                        String date = dateTime.toString("yyyy-MM-dd");
                                        return date;
                                    })
                             .build())
                    .build();
        }
        return type;
    }
    
    /**
     * 点击某一个产品
     * @return
     */
    public static GraphQLFieldDefinition getProductDeviceLogListQueryField() {
        if(productDeviceLogQueryField == null) {
            productDeviceLogQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("productDeviceLogStatistic")
                    .description("某个产品的访问记录列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("productDeviceLogPage").description("某个产品的访问记录类型分页").build())
                    .dataFetcher(environment -> {
                        long id = environment.getArgument("id");
                        ProductStatistic ps = productStatisticMapper.selectByPrimaryKey(id);
                        DeviceLogExample example = new DeviceLogExample();
                        Date startDate = ps.getRecordDate();
                        Date endDate = new DateTime(startDate).plusDays(1).toDate();
                        
                        example.createCriteria().andPIdEqualTo(ps.getProductId()).andCreatedAtBetween(startDate, endDate);
                        List<DeviceLog> list = deviceLogMapper.selectByExample(example);
                        return list;
                    }).build();
        }
        return productDeviceLogQueryField;
    }
    
    public static GraphQLFieldDefinition getDeviceVisitListQueryField() {
        if(deviceVisitListQueryField == null) {
            deviceVisitListQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("deviceVisitStatistics")
                    .description("埋点统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("deviceVisitPage").description("埋点统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        Long id = environment.getArgument("id");
                        Statistic st = statisticMapper.selectByPrimaryKey(id);
                        Date startDate = st.getRecordDate();
                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
                        List<DeviceLog> list = deviceLogMapper.getDeviceLogBetweenDataList(startDate, endDate);
                        return list;
                    } ).build();
        }
        return deviceVisitListQueryField;
    }
    
//    public static GraphQLFieldDefinition getNewUserListQueryField() {
//        if(newUserListQueryField == null) {
//            newUserListQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
//                    .name("newUserStatistics")
//                    .description("埋点统计列表")
//                    .type(PageType.getPageTypeBuidler(getType()).name("newUserStatisticPage").description("埋点统计类型分页").build())
//                    .dataFetcher(environment ->  {
//                        PageHelper.startPage(environment.getArgument("pageNumber"),
//                                environment.getArgument("pageSize"));
//                        Long id = environment.getArgument("id");
//                        Statistic st = statisticMapper.selectByPrimaryKey(id);
//                        Date startDate = st.getRecordDate();
//                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
//                        List<DeviceLog> yesterdayList = deviceLogMapper.getGroupByUserDataList(startDate, endDate);
//                        List<DeviceLog> allList = deviceLogMapper.getAllGroupByUserDataList(startDate);
//                        List<DeviceLog> temp = new ArrayList<>(yesterdayList);
//                        
//                        for (DeviceLog d : allList) {
//                            for (DeviceLog device : yesterdayList) {
//                                if (d.getUserId().equals(device.getUserId())) {
//                                    temp.remove(device);
//                                }
//                            }
//                        }
//                        return temp;
//                    } ).build();
//        }
//        return newUserListQueryField;
//    }
//    
//    public static GraphQLFieldDefinition getUserVisitListQueryField() {
//        if(userVisitListQueryField == null) {
//            userVisitListQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
//                    .name("userVisitStatistics")
//                    .description("埋点统计列表")
//                    .type(PageType.getPageTypeBuidler(getType()).name("userVisitStatisticPage").description("埋点统计类型分页").build())
//                    .dataFetcher(environment ->  {
//                        PageHelper.startPage(environment.getArgument("pageNumber"),
//                                environment.getArgument("pageSize"));
//                        Long id = environment.getArgument("id");
//                        Statistic st = statisticMapper.selectByPrimaryKey(id);
//                        Date startDate = st.getRecordDate();
//                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
//                        List<DeviceLog> list = deviceLogMapper.getGroupByUserDataList(startDate, endDate);
//                        return list;
//                    } ).build();
//        }
//        return userVisitListQueryField;
//    }
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
//                        List<DeviceLog> list = deviceLogMapper.selectByExample(example);
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
//                        List<DeviceLog> list = deviceLogMapper.getAllProductVisitUserDataList(startDate, endDate);
//                        return list;
//                    } ).build();
//        }
//        return totalProductVisitUserListQueryField;
//    }
    
    @Autowired(required = true)
    public void setDeviceLogMapper(DeviceLogMapper deviceLogMapper) {
        DeviceLogType.deviceLogMapper = deviceLogMapper;
    }
    
    @Autowired(required = true)
    public void setStatisticMapper(StatisticMapper statisticMapper) {
        DeviceLogType.statisticMapper = statisticMapper;
    }
    
    @Autowired(required = true)
    public void setProductStatisticMapper(ProductStatisticMapper productStatisticMapper) {
        DeviceLogType.productStatisticMapper = productStatisticMapper;
    }
    
}

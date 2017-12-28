package com.pinganzhiyuan.graphql;

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
import com.pinganzhiyuan.model.Statistic;
import com.pinganzhiyuan.model.User;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class UserType {
    private static GraphQLFieldDefinition newUserListQueryField;
    private static GraphQLFieldDefinition userVisitListQueryField;
//    private static GraphQLFieldDefinition deviceVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitListQueryField;
//    private static GraphQLFieldDefinition totalProductVisitUserListQueryField;
    
    private static UserMapper userMapper;
    private static StatisticMapper statisticMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("User").description("统计")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("username")
                            .description("用户名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("registTime")
                            .description("注册时间")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("name")
                            .description("用户姓名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityNo")
                            .description("身份ID")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("residenceAddr")
                            .description("居住地")
                            .type(Scalars.GraphQLString)
                            .build())
                    // TODO
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isMan")
                            .description("性别")
                            .type(Scalars.GraphQLBoolean)
//                            .dataFetcher(environment -> {
//                                User u = environment.getSource();
//                                return u.getIsMan();
//                            })
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("nation")
                            .description("国家")
                            .type(Scalars.GraphQLString)
                            .build())
                    // TODO
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("birthday")
                            .description("生日")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                                User source = environment.getSource();
                                if (source.getBirthday() == null) {
                                    return "未知";
                                }
                                DateTime dateTime = new DateTime(source.getBirthday());
                                String date = dateTime.toString("yyyy-MM-dd");
                                return date;
                            })
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityAuth")
                            .description("分身授权")
                            .type(Scalars.GraphQLString)
                            .build())
                    // TODO
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityExpiration")
                            .description("身份过期")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                                User source = environment.getSource();
                                DateTime dateTime = new DateTime(source.getIdentityExpiration());
                                String date = dateTime.toString("yyyy-MM-dd");
                                return date;
                            })
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("education")
                            .description("知识面貌")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("guarantee")
                            .description("担保物品")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("profession")
                            .description("专业")
                            .type(Scalars.GraphQLString)
                            .build())
                    .build();
        }
        return type;
    }
    
    public static GraphQLFieldDefinition getNewUserListQueryField() {
        if(newUserListQueryField == null) {
            newUserListQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("newUserStatistics")
                    .description("埋点统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("newUserStatisticPage").description("埋点统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        Long id = environment.getArgument("id");
                        Statistic st = statisticMapper.selectByPrimaryKey(id);
                        Date startDate = st.getRecordDate();
                        // TODO 很奇怪，为什么这里不能加上一天
//                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
                        List<User> yesterdayList = userMapper.gitNewUserDataList(startDate, startDate);
                        return yesterdayList;
                    } ).build();
        }
        return newUserListQueryField;
    }
    
    public static GraphQLFieldDefinition getUserVisitListQueryField() {
        if(userVisitListQueryField == null) {
            userVisitListQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("userVisitStatistics")
                    .description("埋点统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("userVisitStatisticPage").description("埋点统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        Long id = environment.getArgument("id");
                        Statistic st = statisticMapper.selectByPrimaryKey(id);
                        Date startDate = st.getRecordDate();
                        Date endDate = new DateTime(st.getRecordDate()).plusDays(1).toDate();
                        List<User> list = userMapper.getUserVisitRecordDataList(startDate, endDate);
//                        List<DeviceLog> list = deviceMapper.getGroupByUserDataList(startDate, endDate);
                        return list;
                    } ).build();
        }
        return userVisitListQueryField;
    }
    
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
    public void setStatisticMapper(StatisticMapper statisticMapper) {
        UserType.statisticMapper = statisticMapper;
    }

    @Autowired(required = true)
    public  void setUserMapper(UserMapper userMapper) {
        UserType.userMapper = userMapper;
    }
    
}

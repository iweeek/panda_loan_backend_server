package com.pinganzhiyuan.graphql;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.model.Statistic;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;

@Component
public class StatisticType {
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static StatisticMapper statisticMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Statistic").description("统计")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("recordDate")
                            .description("统计时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                                Statistic source = environment.getSource();
                                DateTime dateTime = new DateTime(source.getRecordDate());
                                String date = dateTime.toString("yyyy-MM-dd");
                                return date;
                            })
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("newDeviceCount")
                            .description("新增设备数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("newUserCount")
                            .description("新增用户数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("deviceVisitCount")
                            .description("访问设备数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userVisitCount")
                            .description("访问用户数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productVisitTotalCount")
                            .description("产品访问总次数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productVisitUserTotalCount")
                            .description("产品访问总人数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("averageProductVisitCount")
                            .description("平均每人浏览产品数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .build();
        }
        return type;
    }

    public static GraphQLFieldDefinition getSingleQueryField() {
        if (singleQueryField == null) {
            singleQueryField = GraphQLFieldDefinition
                    .newFieldDefinition()
                    .name("statistic")
                    .description("statistic")
                    .type(getType())
                    .dataFetcher(environment -> {
                        long id = environment.getArgument("id");
                        Statistic statistic = statisticMapper.selectByPrimaryKey(id);
                        return statistic;
                    }).build();
        }
        return singleQueryField;
    }
    
    public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                    .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                    .name("statistics")
                    .description("埋点统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("StatisticPage").description("埋点统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        List<Statistic> list = statisticMapper.selectByExample(null);
                        return list;
                    } ).build();
        }
        return listQueryField;
    }
    
    @Autowired(required = true)
    public void setStatisticMapper(StatisticMapper statisticMapper) {
        StatisticType.statisticMapper = statisticMapper;
    }
    
}

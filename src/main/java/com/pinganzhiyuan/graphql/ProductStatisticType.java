package com.pinganzhiyuan.graphql;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.ProductStatisticMapper;
import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.model.ProductStatistic;
import com.pinganzhiyuan.model.Statistic;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class ProductStatisticType {
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static ProductStatisticMapper productStatisticMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("ProductStatistic").description("产品统计")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productId")
                            .description("产品ID")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("productTitle")
                            .description("产品标题")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("recordDate")
                            .description("统计时间")
                            .type(Scalars.GraphQLString)
                            .dataFetcher(environment ->  {
                                ProductStatistic source = environment.getSource();
                                DateTime dateTime = new DateTime(source.getRecordDate());
                                String date = dateTime.toString("yyyy-MM-dd");
                                return date;
                            })
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("visitCount")
                            .description("每个产品访问次数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("visitUserCount")
                            .description("每个产品访问人数")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("averageStayTime")
                            .description("每个产品的平均停留时长")
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
                    .name("productStatistic")
                    .description("productStatistic")
                    .type(getType())
                    .dataFetcher(environment -> {
                        long id = environment.getArgument("id");
                        ProductStatistic statistic = productStatisticMapper.selectByPrimaryKey(id);
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
                    .name("productStatistics")
                    .description("产品统计列表")
                    .type(PageType.getPageTypeBuidler(getType()).name("ProductStatisticPage").description("产品统计类型分页").build())
                    .dataFetcher(environment ->  {
                        PageHelper.startPage(environment.getArgument("pageNumber"),
                                environment.getArgument("pageSize"));
                        List<ProductStatistic> list = productStatisticMapper.selectByExample(null);
                        return list;
                    } ).build();
        }
        return listQueryField;
    }
    
    /**
     * void前不能加static
     * @param productStatisticMapper
     */
    @Autowired(required = true)
    public void setProductStatisticMapper(ProductStatisticMapper productStatisticMapper) {
        ProductStatisticType.productStatisticMapper = productStatisticMapper;
    }
}

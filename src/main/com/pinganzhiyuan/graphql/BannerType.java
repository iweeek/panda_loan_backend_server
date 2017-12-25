package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.CreditAuthMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.CreditAuth;
import com.pinganzhiyuan.model.CreditAuthExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductExample;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class BannerType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static BannerMapper bannerMapper;

    private static GraphQLObjectType type;
   

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Baner").description("Banner")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("creditCeiling")
                            .description("贷款上限")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("rate")
                            .description("成功率")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("slideNews")
                            .description("轮播新闻")
                            .type(Scalars.GraphQLString)
                            .build())
                    .build();
        }
        return type;
    }

    public static GraphQLFieldDefinition getSingleQueryField() {
        if (singleQueryField == null) {
            singleQueryField = GraphQLFieldDefinition
                    .newFieldDefinition()
                    .name("banner")
                    .description("banner")
                    .type(getType())
                    .dataFetcher(environment -> {
                        return bannerMapper.selectByExample(null);
                    }).build();
        }
        return singleQueryField;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .name("CreditAuths")
//                    .description("信用资质列表")
//                    .type(new GraphQLList(getType()))
//                    .dataFetcher(environment ->  {
//                        CreditAuthExample example = new CreditAuthExample();
//                        example.setOrderByClause("weight asc");
//                        List<CreditAuth> list = creditAuthMapper.selectByExample(example);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    @Autowired(required = true)
    public void setProductMapper(BannerMapper bannerMapper) {
        BannerType.bannerMapper = bannerMapper;
    }
}

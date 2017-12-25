package com.pinganzhiyuan.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.BannerNewsMapper;
import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class BannerType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static BannerMapper bannerMapper;
    private static BannerNewsMapper bannerNewsMapper;
    

    private static GraphQLObjectType type;
   

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Banner").description("Banner")
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
                            .newFieldDefinition().name("passRate")
                            .description("成功率")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("unit")
                            .description("单位")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("url")
                            .description("跳转地址")
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
                        return bannerMapper.selectByExample(null).get(0);
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
    public void setBannerMapper(BannerMapper bannerMapper) {
        BannerType.bannerMapper = bannerMapper;
    }
    
    @Autowired(required = true)
    public void setBannerNewsMapper(BannerNewsMapper bannerNewsMapper) {
        BannerType.bannerNewsMapper = bannerNewsMapper;
    }
}

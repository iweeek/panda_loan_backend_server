package com.pinganzhiyuan.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.BannerNewsMapper;
import com.pinganzhiyuan.mapper.GuaranteeMapper;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class GuaranteeType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static GuaranteeMapper guaranteeMapper;

    private static GraphQLObjectType type;
   

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Guarantee").description("Guarantee")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("creditGuarantee")
                            .description("贷款担保")
                            .type(Scalars.GraphQLString)
                            .build())
                    .build();
        }
        return type;
    }

//    public static GraphQLFieldDefinition getSingleQueryField() {
//        if (singleQueryField == null) {
//            singleQueryField = GraphQLFieldDefinition
//                    .newFieldDefinition()
//                    .name("banner")
//                    .description("banner")
//                    .type(getType())
//                    .dataFetcher(environment -> {
//                        return bannerMapper.selectByExample(null).get(0);
//                    }).build();
//        }
//        return singleQueryField;
//    }
    
    public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("guarantees")
                    .description("信用资质列表")
                    .type(new GraphQLList(getType()))
                    .dataFetcher(environment ->  {
                        return guaranteeMapper.selectByExample(null);
                    } ).build();
        }
        return listQueryField;
    }
    
    @Autowired(required = true)
    public void setGuaranteeMapper(GuaranteeMapper guaranteeMapper) {
        GuaranteeType.guaranteeMapper = guaranteeMapper;
    }
    
}

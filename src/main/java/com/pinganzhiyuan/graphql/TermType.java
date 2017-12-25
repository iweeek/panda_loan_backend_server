package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.LoanAmountRangeMapper;
import com.pinganzhiyuan.mapper.TermMapper;
import com.pinganzhiyuan.model.LoanAmountRange;
import com.pinganzhiyuan.model.LoanAmountRangeExample;
import com.pinganzhiyuan.model.Term;
import com.pinganzhiyuan.model.TermExample;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class TermType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static TermMapper termMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Term").description("贷款期限")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("duration")
                            .description("期限")
                            .type(Scalars.GraphQLInt)
                            .build())
                    .build();
        }
        return type;
    }

//    public static GraphQLFieldDefinition getSingleQueryField() {
//        if (singleQueryField == null) {
//            singleQueryField = GraphQLFieldDefinition
//                    .newFieldDefinition()
//                    .name("首页推荐产品")
//                    .description("首页推荐产品")
//                    .type(getType())
//                    .dataFetcher(environment -> {
//                        
//                    }).build();
//        }
//        return singleQueryField;
//    }
    
    public static GraphQLFieldDefinition getListQueryField() {
        if(listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
                    .name("terms")
                    .description("贷款期限列表")
                    .type(new GraphQLList(getType()))
                    .dataFetcher(environment ->  {
                        TermExample example = new TermExample();
                        example.setOrderByClause("sequency asc");
                        List<Term> list = termMapper.selectByExample(example);
                        return list;
                    } ).build();
        }
        return listQueryField;
    }
    
    @Autowired(required = true)
    public void setTermMapper(TermMapper termMapper) {
        TermType.termMapper = termMapper;
    }
}

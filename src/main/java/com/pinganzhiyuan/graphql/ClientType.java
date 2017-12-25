package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.LoanAmountRangeMapper;
import com.pinganzhiyuan.mapper.TermMapper;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.ClientExample.Criteria;
import com.pinganzhiyuan.model.LoanAmountRange;
import com.pinganzhiyuan.model.LoanAmountRangeExample;
import com.pinganzhiyuan.model.Term;
import com.pinganzhiyuan.model.TermExample;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class ClientType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static ClientMapper clientMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Client").description("客户信息")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户Id")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("name")
                            .description("姓名")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityNo")
                            .description("身份证号")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("residenceAddr")
                            .description("居住地址")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isMan")
                            .description("性别")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("nation")
                            .description("民族")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("birthday")
                            .description("生日")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityAuth")
                            .description("身份证签发机关")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityExpiration")
                            .description("身份证过期时间")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("education")
                            .description("学历")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("guarantee")
                            .description("担保物")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("profession")
                            .description("职业")
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
                    .argument(GraphQLArgument.newArgument().name("userId").type(Scalars.GraphQLLong).build())
                    .name("client")
                    .description("客户信息")
                    .type(getType())
                    .dataFetcher(environment -> {
                        ClientExample example = new ClientExample();
                        Criteria criteria = example.createCriteria();
                        Long userId = environment.getArgument("userId");
                        if (userId != null) {
                            criteria.andUserIdEqualTo(userId);
                        }
                        
                        List<Client> list = clientMapper.selectByExample(example);
                        if (list.size() != 0) {
                            return list.get(0);
                        } else {
                            return null;
                        }
                        
                    }).build();
        }
        return singleQueryField;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .name("terms")
//                    .description("贷款期限列表")
//                    .type(new GraphQLList(getType()))
//                    .dataFetcher(environment ->  {
//                        TermExample example = new TermExample();
//                        example.setOrderByClause("sequency asc");
//                        List<Term> list = termMapper.selectByExample(example);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    @Autowired(required = true)
    public void setClientMapper(ClientMapper clientMapper) {
        ClientType.clientMapper = clientMapper;
    }
}

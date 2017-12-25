package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.CategoryMapper;
import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.CreditAuthMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.Category;
import com.pinganzhiyuan.model.CategoryExample;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
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
public class ClientType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static ClientMapper clientMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("Client").description("Client")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("userId")
                            .description("用户ID")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("name")
                            .description("手机号")
                            .type(Scalars.GraphQLString)
                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("identityNo")
//                            .description("密码")
//                            .type(Scalars.GraphQLString)
//                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("residenceAddr")
                            .description("住址")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("isMan")
                            .description("性别")
                            .type(Scalars.GraphQLBoolean)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("nation")
                            .description("国家")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("birthday")
                            .description("生日")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityAuth")
                            .description("授权码")
                            .type(Scalars.GraphQLString)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("identityExpiration")
                            .description("过期时间")
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
                    .argument(GraphQLArgument.newArgument().name("id").type(Scalars.GraphQLLong).build())
                    .name("client")
                    .description("client")
                    .type(getType())
                    .dataFetcher(environment -> {
                        Long id = environment.getArgument("id");
                        return clientMapper.selectByPrimaryKey(id);
                    }).build();
        }
        return singleQueryField;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .name("client")
//                    .description("客户列表")
//                    .type(new GraphQLList(getType()))
//                    .dataFetcher(environment ->  {
//                        ClientExample example = new ClientExample();
//                        example.setOrderByClause("weight asc");
//                        List<Client> list = clientMapper.selectByExample(example);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    @Autowired(required = true)
    public void setClientMapper(ClientMapper clientMapper) {
        ClientType.clientMapper= clientMapper;
    }
}

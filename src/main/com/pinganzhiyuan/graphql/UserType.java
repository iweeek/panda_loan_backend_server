package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.BannerMapper;
import com.pinganzhiyuan.mapper.CategoryMapper;
import com.pinganzhiyuan.mapper.ClientMapper;
import com.pinganzhiyuan.mapper.CreditAuthMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.mapper.UserMapper;
import com.pinganzhiyuan.model.Category;
import com.pinganzhiyuan.model.CategoryExample;
import com.pinganzhiyuan.model.Client;
import com.pinganzhiyuan.model.ClientExample;
import com.pinganzhiyuan.model.CreditAuth;
import com.pinganzhiyuan.model.CreditAuthExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.model.User;
import com.pinganzhiyuan.model.UserExample;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class UserType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static UserMapper userMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("User").description("User")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("id")
                            .description("唯一主键")
                            .type(Scalars.GraphQLLong)
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("username")
                            .description("用户名/手机")
                            .type(Scalars.GraphQLString)
                            .build())
//                    .field(GraphQLFieldDefinition
//                            .newFieldDefinition().name("password")
//                            .description("手机号")
//                            .type(Scalars.GraphQLString)
//                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("registTime")
                            .description("注册时间")
                            .type(Scalars.GraphQLLong)
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
                    .name("user")
                    .description("user")
                    .type(getType())
                    .dataFetcher(environment -> {
                        Long id = environment.getArgument("id");
                        return userMapper.selectByPrimaryKey(id);
                    }).build();
        }
        return singleQueryField;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .name("Client")
//                    .description("客户列表")
//                    .type(new GraphQLList(getType()))
//                    .dataFetcher(environment ->  {
//                        UserExample example = new UserExample();
//                        example.setOrderByClause("weight asc");
//                        List<User> list = userMapper.selectByExample(example);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    @Autowired(required = true)
    public void setUserMapper(UserMapper userMapper) {
        UserType.userMapper= userMapper;
    }
}

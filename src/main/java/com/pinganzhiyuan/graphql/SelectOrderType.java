package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.SelectOrderMapper;
import com.pinganzhiyuan.model.SelectOrder;
import com.pinganzhiyuan.model.SelectOrderExample;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class SelectOrderType {

    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;

    private static SelectOrderMapper selectOrderMapper;

    private static GraphQLObjectType type;

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType.newObject().name("SelectOrder").description("贷款产品顺序")
                    .field(GraphQLFieldDefinition.newFieldDefinition().name("id").description("唯一主键")
                            .type(Scalars.GraphQLLong).build())
                    .field(GraphQLFieldDefinition.newFieldDefinition().name("name").description("名称")
                            .type(Scalars.GraphQLString).build())
                    .field(GraphQLFieldDefinition.newFieldDefinition().name("field").description("产品表的域")
                            .type(Scalars.GraphQLString).build())
                    .build();
        }
        return type;
    }

    // public static GraphQLFieldDefinition getSingleQueryField() {
    // if (singleQueryField == null) {
    // singleQueryField = GraphQLFieldDefinition
    // .newFieldDefinition()
    // .name("首页推荐产品")
    // .description("首页推荐产品")
    // .type(getType())
    // .dataFetcher(environment -> {
    //
    // }).build();
    // }
    // return singleQueryField;
    // }

    public static GraphQLFieldDefinition getListQueryField() {
        if (listQueryField == null) {
            listQueryField = GraphQLFieldDefinition.newFieldDefinition().name("selectOrders").description("贷款产品顺序列表")
                    .type(new GraphQLList(getType())).dataFetcher(environment -> {
                        List<SelectOrder> list = selectOrderMapper.selectByExample(null);
                        return list;
                    }).build();
        }
        return listQueryField;
    }

    @Autowired(required = true)
    public void setSelectOrderMapper(SelectOrderMapper selectOrderMapper) {
        SelectOrderType.selectOrderMapper = selectOrderMapper;
    }
}

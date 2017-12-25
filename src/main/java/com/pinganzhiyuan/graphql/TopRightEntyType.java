package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.mapper.TopNavMapper;
import com.pinganzhiyuan.mapper.TopRightEntryMapper;
import com.pinganzhiyuan.model.TopNav;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class TopRightEntyType {
    
    private static GraphQLFieldDefinition singleQueryField;
    private static GraphQLFieldDefinition listQueryField;
    
    private static TopRightEntryMapper topRightEntyMapper;

    private static GraphQLObjectType type;
   

    public static GraphQLObjectType getType() {
        if (type == null) {
            type = GraphQLObjectType
                    .newObject().name("TopRightEnty").description("顶部右侧入口（七日排行）")
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("title")
                            .description("名称")
                            .type(Scalars.GraphQLString)
                            .staticValue("七日排行")
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("selectParam")
                            .description("选择参数名")
                            .type(Scalars.GraphQLString)
                            .staticValue("typeId")
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("iconUrl")
                            .description("图标地址")
                            .type(Scalars.GraphQLString)
                            .staticValue("")
                            .build())
                    .field(GraphQLFieldDefinition
                            .newFieldDefinition().name("paramValue")
                            .description("选择参数值")
                            .type(Scalars.GraphQLInt)
                            .staticValue(1)
                            .build())
                    .build();
        }
        return type;
    }
    
//    public static GraphQLFieldDefinition getTitleField() {
//        if (singleQueryField == null) {
//            singleQueryField = GraphQLFieldDefinition
//                    .newFieldDefinition()
//                    .name("topRightEntryName")
//                    .description("名称")
//                    .type(Scalars.GraphQLString)
//                    .staticValue("七日排行")
//                    .build()
//                    .name("topRightEntryTypeId")
//                    .description("选择类型Id")
//                    .type(Scalars.GraphQLInt)
//                    .staticValue(1)
//                    .build();
//        }
//        return singleQueryField;
//    }

    public static GraphQLFieldDefinition getSingleQueryField() {
        if (singleQueryField == null) {
            singleQueryField = GraphQLFieldDefinition
                    .newFieldDefinition()
                    .name("topRightEntry")
                    .description("顶部右侧入口")
                    .type(getType())
                    .staticValue(type)
                    .build();
        }
        return singleQueryField;
    }
    
//    public static GraphQLFieldDefinition getListQueryField() {
//        if(listQueryField == null) {
//            listQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .name("topRightEntries")
//                    .description("顶部右侧导航列表")
//                    .type(new GraphQLList(getType()))
//                    .dataFetcher(environment ->  {
//                        List<TopNav> list = topNavMapper.selectByExample(null);
//                        return list;
//                    } ).build();
//        }
//        return listQueryField;
//    }
    
    @Autowired(required = true)
    public void setProductMapper(TopRightEntryMapper topRightEntyMapper) {
        TopRightEntyType.topRightEntyMapper = topRightEntyMapper;
    }
}

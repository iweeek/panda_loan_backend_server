package com.pinganzhiyuan.graphql;

import com.github.pagehelper.Page;

import graphql.Scalars;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLObjectType.Builder;

public class PageType {
	public static Builder getPageTypeBuidler(GraphQLObjectType type) {
		return GraphQLObjectType.newObject()
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("pageNum")
							.description("页码")
							.type(Scalars.GraphQLInt)
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("pageSize")
							.description("页面大小")
							.type(Scalars.GraphQLInt)
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("pagesCount")
							.description("页面总数")
							.dataFetcher(environment ->  {
								Page<?> page = environment.getSource();
			                	return page.getPages();
							} )
							.type(Scalars.GraphQLInt)
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("dataCount")
							.description("数据总数")
							.type(Scalars.GraphQLInt)
							.dataFetcher(environment ->  {
								Page<?> page = environment.getSource();
			                	return page.getTotal();
							} )
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("data")
							.description("数据")
							.type(new GraphQLList(type))
							.dataFetcher(environment -> {
								return  environment.getSource();
							})
							.build());
	}
}	

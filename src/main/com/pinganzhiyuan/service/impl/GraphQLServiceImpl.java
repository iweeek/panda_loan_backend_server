package com.pinganzhiyuan.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pinganzhiyuan.graphql.BannerType;
import com.pinganzhiyuan.graphql.CategoryType;
import com.pinganzhiyuan.graphql.ClientType;
import com.pinganzhiyuan.graphql.CreditAuthType;
import com.pinganzhiyuan.graphql.NavType;
import com.pinganzhiyuan.graphql.RecommendProductType;
import com.pinganzhiyuan.graphql.UserType;
import com.pinganzhiyuan.model.CreditAuth;
import com.pinganzhiyuan.service.GraphQLService;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;


/**
* GraphQL查询服务实现类
* 
* @author x1ny
* @date 2017年5月24日
*/
@Service
public class GraphQLServiceImpl implements GraphQLService {

	private GraphQL graphQL;
	
	public GraphQLServiceImpl() {
		//创建GraphQL实例
		GraphQLObjectType queryType = GraphQLObjectType.newObject()
                .name("root")
                .field(RecommendProductType.getListQueryField())
                .field(CreditAuthType.getListQueryField())
                .field(BannerType.getSingleQueryField())
                .field(NavType.getListQueryField())
                .field(CategoryType.getListQueryField())
                .field(UserType.getSingleQueryField())
                .field(ClientType.getSingleQueryField())
//                .field(CategoryType.getSingleQueryField())
                .build();
		
		GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();
		
		graphQL = GraphQL.newGraphQL(schema).build();
		// 手动输出
//		Map<String, Object> result = graphQL.execute("").getData();
	}
	

	/* (non-Javadoc)
	 * @see com.wzsport.service.GraphQLService#query(java.lang.String)
	 */
	public ExecutionResult query(String queryString) {
		return graphQL.execute(queryString);
	}


	/* (non-Javadoc)
	 * @see com.wzsport.service.GraphQLService#query(java.lang.String, java.util.Map)
	 */
	@Override
	public ExecutionResult query(String queryString, Map<String, Object> variables) {
		return graphQL.execute(queryString, (Object) null, variables);
	}
}

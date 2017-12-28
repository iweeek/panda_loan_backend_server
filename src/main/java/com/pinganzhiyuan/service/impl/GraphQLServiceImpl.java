package com.pinganzhiyuan.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pinganzhiyuan.graphql.BannerNewsType;
import com.pinganzhiyuan.graphql.BannerType;
import com.pinganzhiyuan.graphql.ClientType;
import com.pinganzhiyuan.graphql.ClientVersionType;
import com.pinganzhiyuan.graphql.CreditAuthType;
import com.pinganzhiyuan.graphql.DeviceLogType;
import com.pinganzhiyuan.graphql.DeviceType;
import com.pinganzhiyuan.graphql.GuaranteeType;
import com.pinganzhiyuan.graphql.LoanAmountRangeType;
import com.pinganzhiyuan.graphql.MidAdType;
import com.pinganzhiyuan.graphql.MidNavType;
import com.pinganzhiyuan.graphql.ProductStatisticType;
import com.pinganzhiyuan.graphql.TopNavType;
import com.pinganzhiyuan.graphql.TopRightEntyType;
import com.pinganzhiyuan.graphql.UserType;
import com.pinganzhiyuan.graphql.RecommendProductType;
import com.pinganzhiyuan.graphql.SelectOrderType;
import com.pinganzhiyuan.graphql.StatisticType;
import com.pinganzhiyuan.graphql.TermType;
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
                .field(RecommendProductType.getSearchListField())
                .field(CreditAuthType.getListQueryField())
                .field(BannerType.getSingleQueryField())
                .field(TopNavType.getListQueryField())
                .field(MidNavType.getListQueryField())
                .field(BannerNewsType.getListQueryField())
                .field(MidAdType.getListQueryField())
                .field(GuaranteeType.getListQueryField())
                .field(LoanAmountRangeType.getListQueryField())
                .field(SelectOrderType.getListQueryField())
                .field(TermType.getListQueryField())
                .field(ClientVersionType.getLatestVerisonQueryField())
                .field(ClientVersionType.getVerisonQueryField())
                .field(TopRightEntyType.getSingleQueryField())
                .field(ClientType.getSingleQueryField())
                .field(StatisticType.getListQueryField())
                .field(StatisticType.getSingleQueryField())
                .field(ProductStatisticType.getListQueryField())
                .field(ProductStatisticType.getSingleQueryField())
                .field(DeviceLogType.getDeviceVisitListQueryField())
                .field(DeviceType.getNewDeviceListQueryField())
                .field(UserType.getNewUserListQueryField())
                .field(UserType.getUserVisitListQueryField())
                
                .build();
		
		GraphQLSchema schema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();
		
		graphQL = GraphQL.newGraphQL(schema).build();
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
	public ExecutionResult query(String queryString, Map<String, Object> variables) {
		return graphQL.execute(queryString, (Object) null, variables);
	}
}

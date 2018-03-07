package com.pinganzhiyuan.graphql;

import java.awt.Panel;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinganzhiyuan.mapper.AppClientMapper;
import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ClientVersionExample.Criteria;
import com.pinganzhiyuan.service.AppClientService;
import com.pinganzhiyuan.service.ChannelService;
import com.pinganzhiyuan.service.ClientVersionService;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class ClientVersionType {

	private static ClientVersionService clientVersionService;
	private static AppClientMapper appClientMapper;
	private static ChannelMapper channelMapper;
	private static ClientVersionMapper clientVersionMapper;
	
	private static GraphQLObjectType type;
	private static GraphQLFieldDefinition cascadeQueryField;
	private static GraphQLFieldDefinition verisonMaskQueryField;
	
	public static GraphQLObjectType getType() {
		if(type == null) {
			type = GraphQLObjectType.newObject()
					.name("ClientVersion")
					.description("客户端版本信息")
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("id")
							.type(Scalars.GraphQLLong)
							.description("唯一主键")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("name")
							.type(Scalars.GraphQLString)
							.description("上架产品名字")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("logoUrl")
							.type(Scalars.GraphQLString)
							.description("logo的地址")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("versionName")
							.type(Scalars.GraphQLString)
							.description("版本名称")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("versionCode")
							.type(Scalars.GraphQLInt)
							.description("版本号")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("changeLog")
							.type(Scalars.GraphQLString)
							.description("版本更新信息")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("downloadUrl")
							.type(Scalars.GraphQLString)
							.description("安装包下载地址")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("isForced")
							.type(Scalars.GraphQLBoolean)
							.description("是否强制安装")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("platformId")
							.type(Scalars.GraphQLByte)
							.description("客户端平台Id，0：安卓；1：iOS")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
                            .name("maskSwitch")
                            .type(Scalars.GraphQLBoolean)
                            .description("遮挡层开关")
                            .build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
                            .name("channelId")
                            .type(Scalars.GraphQLString)
                            .description("渠道Id")
                            .build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
                            .name("channelName")
                            .type(Scalars.GraphQLString)
                            .description("渠道名称")
                            .build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("packageName")
							.type(Scalars.GraphQLString)
							.description("包名")
							.build())
					.field(GraphQLFieldDefinition.newFieldDefinition()
							.name("isPublished")
							.type(Scalars.GraphQLBoolean)
							.description("是否已发布")
							.build())
					.build();
		}
		return type;
	}
	
   public static GraphQLFieldDefinition getCascadeQueryField() {
       if(cascadeQueryField == null) {
    	   		cascadeQueryField = GraphQLFieldDefinition.newFieldDefinition()
    	   			   .argument(GraphQLArgument.newArgument().name("appName").type(Scalars.GraphQLString).build())
                   .argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLString).build())
                   .argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
                   .argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLInt).build())
                   .argument(GraphQLArgument.newArgument().name("isPublished").type(Scalars.GraphQLInt).build())
                   .name("clientVersionsCascade")
                   .description("级联查询")
                   .type(new GraphQLList(getType()))
                   .dataFetcher(environment ->  {
	            	   	   List<ClientVersion> clientVersions = null;
	                   String channelId = environment.getArgument("channelId");
	                   String appName = environment.getArgument("appName");
	                   String packageName = environment.getArgument("packageName");
	                   Integer platformId = environment.getArgument("platformId");
	                   Integer isPublished = environment.getArgument("isPublished");
	                   
	                   ClientVersionExample example = new ClientVersionExample();
                       Criteria criteria = example.createCriteria();
                       
	                   if (channelId != null && !channelId.equals("")) {
	                	   		criteria.andChannelIdEqualTo(Long.parseLong(channelId));
	                   }
	                   if (platformId != null) {
	                	   		criteria.andPlatformIdEqualTo((byte) platformId.intValue());
	                   }
	                   
	                   if (appName != null && !appName.equals("")) {
	                	   		criteria.andNameEqualTo(appName);
	                   }
	                   
	                   if (packageName != null && !packageName.equals("")) {
	                	   		criteria.andPackageNameEqualTo(packageName);
	                   }
	                   if (isPublished != null) {
	                	   		if (isPublished == 1) {
	                	   			criteria.andIsPublishedEqualTo(true);
	                	   		} else {
	                	   			criteria.andIsPublishedEqualTo(false);
	                	   		}
	                   }
	                   
	                   clientVersions = clientVersionMapper.selectByExample(example);
	                   return clientVersions;
	               } ).build();
	   }
	   return cascadeQueryField;
   }
   
   public static GraphQLFieldDefinition getListQueryField() {
       if(verisonMaskQueryField == null) {
           verisonMaskQueryField = GraphQLFieldDefinition.newFieldDefinition()
        		   	   .argument(GraphQLArgument.newArgument().name("appName").type(Scalars.GraphQLString).build())
                   .argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLLong).build())
                   .argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLInt).build())
                   .argument(GraphQLArgument.newArgument().name("isPublished").type(Scalars.GraphQLInt).build())
                   .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                   .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                   .name("clientVersions")
                   .description("获取某个版本信息")
                   .type(PageType.getPageTypeBuidler(getType())
                		   .name("clientVersionPage")
                		   .description("获取某个版本分页信息")
                		   .build())
                   .dataFetcher(environment ->  {
                	   	   List<ClientVersion> clientVersions = null;
                       Long channelId = environment.getArgument("channelId");
                       String appName = environment.getArgument("appName");
                       Integer platformId = environment.getArgument("platformId");
                       Integer pageNumber = environment.getArgument("pageNumber");
                       Integer pageSize = environment.getArgument("pageSize");
                       Integer isPublished = environment.getArgument("isPublished");
                       
                       ClientVersionExample example = new ClientVersionExample();
                       Criteria criteria = example.createCriteria();
                       
                       example.setOrderByClause(" version_code desc ");
                       
                       if (channelId != null) {
                    	   		criteria.andChannelIdEqualTo(channelId);
                       }
                       if (appName != null) {
                    	   		criteria.andNameEqualTo(appName);
                       }
                       if (platformId != null) {
                    	   		criteria.andPlatformIdEqualTo((byte) platformId.intValue());
                       }
                       if (isPublished != null) {
	               	   		if (isPublished == 1) {
	               	   			criteria.andIsPublishedEqualTo(true);
	               	   		} else {
	               	   			criteria.andIsPublishedEqualTo(false);
	               	   		}
                       }
                       
                       PageHelper.startPage(pageNumber, pageSize);
               		   clientVersions = clientVersionMapper.selectByExample(example);
                       return clientVersions;
                   } ).build();
       }
       return verisonMaskQueryField;
   }
	
	@Autowired(required = true) 
	public void setClientVersionService(ClientVersionService clientVersionService) {
		ClientVersionType.clientVersionService = clientVersionService;
	}
	
	@Autowired(required = true) 
	public void setAppClientMapper(AppClientMapper appClientMapper) {
		ClientVersionType.appClientMapper = appClientMapper;
	}
	@Autowired(required = true) 
	public void setChannelMapper(ChannelMapper channelMapper) {
		ClientVersionType.channelMapper = channelMapper;
	}
	@Autowired(required = true) 
	public void setClientVersionMapper(ClientVersionMapper clientVersionMapper) {
		ClientVersionType.clientVersionMapper = clientVersionMapper;
	}
}




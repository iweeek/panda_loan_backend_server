package com.pinganzhiyuan.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.model.ClientVersionExample.Criteria;
import com.pinganzhiyuan.service.ClientVersionService;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLObjectType;

@Component
public class ClientVersionType {

	private static ClientVersionService clientVersionService;
	private static GraphQLObjectType type;
	private static GraphQLFieldDefinition latestVerisonQueryField;
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
					.build();
		}
		
		return type;
	}
	
//	public static GraphQLFieldDefinition getLatestVerisonQueryField() {
//		if(latestVerisonQueryField == null) {
//			latestVerisonQueryField = GraphQLFieldDefinition.newFieldDefinition()
//					.argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//					.argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//					.argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//	                .name("latestVersion")
//	                .description("获取客户端最新版本信息")
//	                .type(getType())
//	                .dataFetcher(environment ->  {
//	                	byte platformId = environment.getArgument("platformId");
//	                	int channelId = environment.getArgument("channelId");
//	                	String packageName = environment.getArgument("packageName");
//	                	return clientVersionService.getLatestVersionInfo(platformId, channelId, packageName);
//	                } ).build();
//		}
//        return latestVerisonQueryField;
//    }
//	public static GraphQLFieldDefinition getLatestVerisonQueryField() {
//		if(latestVerisonQueryField == null) {
//			latestVerisonQueryField = GraphQLFieldDefinition.newFieldDefinition()
//					.argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//					.argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//					.argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//	                .name("latestVersion")
//	                .description("获取客户端最新版本信息")
//	                .type(getType())
//	                .dataFetcher(environment ->  {
//	                	byte platformId = environment.getArgument("platformId");
//	                	int channelId = environment.getArgument("channelId");
//	                	String packageName = environment.getArgument("packageName");
//	                	return clientVersionService.getLatestVersionInfo(platformId, channelId, packageName);
//	                } ).build();
//		}
//        return latestVerisonQueryField;
//    }
//	public static GraphQLFieldDefinition getLatestVerisonQueryField() {
//		if(latestVerisonQueryField == null) {
//			latestVerisonQueryField = GraphQLFieldDefinition.newFieldDefinition()
//					.argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//					.argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//					.argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//	                .name("latestVersion")
//	                .description("获取客户端最新版本信息")
//	                .type(getType())
//	                .dataFetcher(environment ->  {
//	                	byte platformId = environment.getArgument("platformId");
//	                	int channelId = environment.getArgument("channelId");
//	                	String packageName = environment.getArgument("packageName");
//	                	return clientVersionService.getLatestVersionInfo(platformId, channelId, packageName);
//	                } ).build();
//		}
//        return latestVerisonQueryField;
//    }
//	public static GraphQLFieldDefinition getLatestVerisonQueryField() {
//		if(latestVerisonQueryField == null) {
//			latestVerisonQueryField = GraphQLFieldDefinition.newFieldDefinition()
//					.argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//					.argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//					.argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//	                .name("latestVersion")
//	                .description("获取客户端最新版本信息")
//	                .type(getType())
//	                .dataFetcher(environment ->  {
//	                	byte platformId = environment.getArgument("platformId");
//	                	int channelId = environment.getArgument("channelId");
//	                	String packageName = environment.getArgument("packageName");
//	                	return clientVersionService.getLatestVersionInfo(platformId, channelId, packageName);
//	                } ).build();
//		}
//        return latestVerisonQueryField;
//    }
//	public static GraphQLFieldDefinition getLatestVerisonQueryField() {
//		if(latestVerisonQueryField == null) {
//			latestVerisonQueryField = GraphQLFieldDefinition.newFieldDefinition()
//					.argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//					.argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//					.argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//	                .name("latestVersion")
//	                .description("获取客户端最新版本信息")
//	                .type(getType())
//	                .dataFetcher(environment ->  {
//	                	byte platformId = environment.getArgument("platformId");
//	                	int channelId = environment.getArgument("channelId");
//	                	String packageName = environment.getArgument("packageName");
//	                	return clientVersionService.getLatestVersionInfo(platformId, channelId, packageName);
//	                } ).build();
//		}
//        return latestVerisonQueryField;
//    }
	
//   public static GraphQLFieldDefinition getVerisonQueryField() {
//        if(verisonMaskQueryField == null) {
//            verisonMaskQueryField = GraphQLFieldDefinition.newFieldDefinition()
//                    .argument(GraphQLArgument.newArgument().name("platformId").type(Scalars.GraphQLByte).build())
//                    .argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLInt).build())
//                    .argument(GraphQLArgument.newArgument().name("packageName").type(Scalars.GraphQLString).build())
//                    .argument(GraphQLArgument.newArgument().name("versionCode").type(Scalars.GraphQLInt).build())
//                    .name("version")
//                    .description("获取某个版本信息")
//                    .type(getType())
//                    .dataFetcher(environment ->  {
//                        byte platformId = environment.getArgument("platformId");
//                        int channelId = environment.getArgument("channelId");
//                        String packageName = environment.getArgument("packageName");
//                        int versionCode = environment.getArgument("versionCode");
//                        return clientVersionService.getVersion(platformId, channelId, packageName, versionCode);
//                    } ).build();
//        }
//        return verisonMaskQueryField;
//    }
   
   public static GraphQLFieldDefinition getListQueryField() {
       if(verisonMaskQueryField == null) {
           verisonMaskQueryField = GraphQLFieldDefinition.newFieldDefinition()
        		   	   .argument(GraphQLArgument.newArgument().name("appName").type(Scalars.GraphQLString).build())
                   .argument(GraphQLArgument.newArgument().name("channelId").type(Scalars.GraphQLLong).build())
                   .argument(GraphQLArgument.newArgument().name("pageNumber").type(Scalars.GraphQLInt).build())
                   .argument(GraphQLArgument.newArgument().name("pageSize").type(Scalars.GraphQLInt).build())
                   .name("clientVersions")
                   .description("获取某个版本信息")
                   .type(new GraphQLList(getType()))
                   .dataFetcher(environment ->  {
                	   	   List<ClientVersion> clientVersions = null;
                       Long channelId = environment.getArgument("channelId");
                       String appName = environment.getArgument("appName");
                       Integer pageNumber = environment.getArgument("pageNumber");
                       Integer pageSize = environment.getArgument("pageSize");
                       
                       ClientVersionExample example = new ClientVersionExample();
                       Criteria criteria = example.createCriteria();
                       
                       if (channelId != null) {
                    	   		criteria.andChannelIdEqualTo(channelId);
                       }
                       if (appName != null) {
                    	   		criteria.andNameEqualTo(appName);
                       }
                       
                       if (pageNumber != null && pageSize != null) {
                    	   		PageHelper.startPage(pageNumber, pageSize);
                       }
                       
                       clientVersions = clientVersionService.screen();
                       return clientVersions;
                   } ).build();
       }
       return verisonMaskQueryField;
   }
	
	@Autowired(required = true) 
	public void setAndroidVersionInfoService(ClientVersionService androidVersionInfoService) {
		ClientVersionType.clientVersionService = androidVersionInfoService;
	}
}




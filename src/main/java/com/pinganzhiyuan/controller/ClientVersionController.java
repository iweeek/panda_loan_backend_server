package com.pinganzhiyuan.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.AppClientMapper;
import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.service.AppClientService;
import com.pinganzhiyuan.service.ChannelService;
import com.pinganzhiyuan.service.ClientVersionService;
import com.pinganzhiyuan.util.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
* Version Controller.
* 
* @author x1ny
* @date 2017年5月26日
*/
@Api(tags = "Version相关接口")
@RestController
public class ClientVersionController {

	@Autowired
	private ClientVersionService clientVersionService;
	
	@Autowired
    private ChannelService channelService;
	
    @Autowired
    private AppClientMapper appClientMapper;
	
    @ApiOperation(value = "创建版本信息", notes = "创建版本信息")
    @RequestMapping(value = "/clientVersions", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
	public ResponseEntity<?> create(
			@ApiParam("APP名称")
			@RequestParam String name,
			@ApiParam("logo")
			@RequestParam  String logoUrl,
			@ApiParam("版本名称")
			@RequestParam  String versionName,
			@ApiParam("版本号")
			@RequestParam  Integer versionCode,
			@ApiParam("修改记录")
			@RequestParam(required = false)  String changeLog,
			@ApiParam("下载地址")
			@RequestParam(required = false)  String downloadUrl,
			@ApiParam("包名")
			@RequestParam  String packageName,
			@ApiParam("渠道ID")
			@RequestParam Long channelId,
			@ApiParam("是否发布")
			@RequestParam Boolean isPublished)
								{
		ClientVersion info = new ClientVersion();
		info.setName(name);
		info.setLogoUrl(logoUrl);
		info.setVersionName(versionName);
		info.setVersionCode(versionCode);
		info.setChangeLog(changeLog);
		info.setIsForced(false);
		info.setDownloadUrl(downloadUrl);
		info.setPackageName(packageName);
		info.setMaskSwitch(true);
		info.setPublishTime(new Date());
		info.setIsPublished(isPublished);
		
		if (channelId != null) {
			info.setChannelId(channelId);
			if (channelId == 13) {
				info.setPlatformId((byte) 1);
			} else {
				info.setPlatformId((byte) 0);
			}
		}
		
		ResponseBody<ClientVersion> resBody = new ResponseBody<ClientVersion>();
		
		int status = clientVersionService.create(info, resBody);
		return ResponseEntity.status(status).body(resBody); 
	}
	
	@ApiOperation(value = "更新版本信息", notes = "更新版本信息")
	@RequestMapping(value = "/clientVersions/{id}", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
	public ResponseEntity<?> update(
			@ApiParam("id")
            @PathVariable long id,
			@ApiParam("APP名称")
			@RequestParam(required = false)  String name,
			@ApiParam("logo")
			@RequestParam(required = false)  String logoUrl,
			@ApiParam("版本名称")
			@RequestParam(required = false)  String versionName,
			@ApiParam("版本号")
			@RequestParam(required = false)  Integer versionCode,
			@ApiParam("修改记录")
			@RequestParam String changeLog,
			@ApiParam("下载地址")
			@RequestParam(required = false)  String downloadUrl,
			@ApiParam("包名")
			@RequestParam(required = false)  String packageName,
			@ApiParam("渠道ID")
			@RequestParam(required = false)  Long channelId,
			@ApiParam("是否发布")
			@RequestParam(required = false) Boolean isPublished)
								{
		ClientVersion info = new ClientVersion();
		info.setId(id);
		
		if (name != null) {
			info.setName(name);
		}
		
		if (logoUrl != null) {
			info.setLogoUrl(logoUrl);
		}
		
		if (versionName != null) {
			info.setVersionName(versionName);
		}
		
		if (versionCode != null) {
			info.setVersionCode(versionCode);
		}
		
		if (changeLog != null) {
			info.setChangeLog(changeLog);
		}
		
		if (downloadUrl != null) {
			info.setDownloadUrl(downloadUrl);
		}
		
		if (packageName != null) {
			info.setPackageName(packageName);
		}
		
		if (isPublished != null) {
			info.setIsPublished(isPublished);
		}
		
		if (channelId != null) {
			info.setChannelId(channelId);
			if (channelId == 13) {
				info.setPlatformId((byte) 1);
			} else {
				info.setPlatformId((byte) 0);
			}
		}
		
		@SuppressWarnings("rawtypes")
		ResponseBody resBody = new ResponseBody<ClientVersion>();
		
		// 给个默认值
		int status = clientVersionService.update(info, resBody);
		return ResponseEntity.status(status).body(resBody); 
	}
	
//    @SuppressWarnings("rawtypes")
//    @ApiOperation(value = "级联查询", notes = "")
//    @RequestMapping(value = "/cascade", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
//    public ResponseEntity<?> channelsByAppName(HttpServletResponse response, 
//						    		 @ApiParam("渠道")
//						    		 @RequestParam(name = "channelId", required = false) String channelId,
//						    		 @ApiParam("App名称")
//	 							 @RequestParam(name = "appName", required = false) String appName,
//	 							 @ApiParam("包名")
//    								 @RequestParam(name = "packageName", required = false) String packageName
//                                    ) {
//    		int status = HttpServletResponse.SC_NOT_FOUND;
//    		ResponseBody resBody = null;
//    		if (channelId != null) {
//    	        resBody = new ResponseBody<AppClient>();
//    	        status = appClientService.indexAppClientByChannelId(channelId, resBody);
//    		} 
//    		if (appName != null) {
//    			resBody = new ResponseBody<Channel>();
////    			status = channelService.indexChannelByAppName(appName, resBody);
//    			status = clientVersionService.selectByAppName(appName, resBody);
//    		}
//    		if (packageName != null) {
//    			resBody = new ResponseBody<ClientVersion>();
//    	        status = clientVersionService.getAppNameByPackageName(packageName, resBody);
//    		}
//        return ResponseEntity.status(status).body(resBody); 
//    }
	
}

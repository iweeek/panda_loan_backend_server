package com.pinganzhiyuan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.model.ClientVersion;
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
@RestController()
@RequestMapping(value = "/versions",produces = "application/json;charset=UTF-8")
public class ClientVersionController {

	@Autowired
	private ClientVersionService clientVersionService;
	
	/** The res body. */
//	@SuppressWarnings("rawtypes")
//	private ResponseBody resBody;
//	
//	private int status;
	
	/**
	* 创建AndroidVersion接口
	*/
//	@ApiOperation(value = "创建版本信息", notes = "创建一条新的版本信息。")
//	@RequestMapping(value = "", method = RequestMethod.POST)
//	public ResponseEntity<?> create(
//								@ApiParam("版本名称")
//								@RequestParam String versionName,
//								@ApiParam("版本号")
//								@RequestParam Integer versionCode,
//								@ApiParam("修改记录")
//								@RequestParam String changeLog,
//								@ApiParam("是否强制")
//								@RequestParam Boolean isForced,
//								@ApiParam("客户端平台Id，0：Android，1：iOS")
//								@RequestParam Byte platformId,
//								@ApiParam("下载地址")
//								@RequestParam String downloadUrl)
//								{
//		if (platformId != 0 && platformId != 1) {
//			return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
//		}
//		
//		ClientVersion info = new ClientVersion();
//		info.setVersionName(versionName);
//		info.setVersionCode(versionCode);
//		info.setChangeLog(changeLog);
//		info.setIsForced(isForced);
//		
//		info.setPlatformId(platformId);
//		info.setDownloadUrl(downloadUrl);
//		
//		ResponseBody resBody = new ResponseBody<AreaActivity>();
//		
//		int status = clientVersionService.create(info, resBody);
//		return ResponseEntity.status(status).body(resBody); 
//	}
	
	/**
	* 
	*/
//	@SuppressWarnings("rawtypes")
//	@ApiOperation(value = "根据Id获取某一版本信息", notes = "根据Id获取某一版本信息")
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> read(
//								@ApiParam("版本的id")
//								@PathVariable long id)
//								{
//		ClientVersion info = new ClientVersion();
//		info.setId(id);
//		
//		ResponseBody resBody = new ResponseBody<AreaActivity>();
//		
//		int status = clientVersionService.read(info, resBody);
//		
//		return ResponseEntity.status(status).body(resBody); 
//	}
	
	/**
	* 
	*/
//	@ApiOperation(value = "更新版本信息", notes = "更新版本信息")
//	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
//	public ResponseEntity<?> update(
//								@ApiParam("活动项目id")
//								@PathVariable long id,
//								@ApiParam("版本名称")
//								@RequestParam(required=false) String versionName,
//								@ApiParam("版本号")
//								@RequestParam(required=false) Integer versionCode,
//								@ApiParam("修改记录")
//								@RequestParam(required=false) String changeLog,
//								@ApiParam("是否强制")
//								@RequestParam(required=false) Boolean isForced,
//								@ApiParam("客户端平台Id，0：Android，1：iOS")
//								@RequestParam(required=false) Byte platformId,
//								@ApiParam("是否发布")
//								@RequestParam(required=false) Boolean isPublished,
//								@ApiParam("下载地址")
//								@RequestParam(required=false) String downloadUrl)
//								{
//		if (platformId != null && platformId != 0 && platformId != 1) {
//			return ResponseEntity.status(HttpServletResponse.SC_BAD_REQUEST).build();
//		}
//		
//		ClientVersion info = new ClientVersion();
//		info.setId(id);
//		
//		if (versionName != null) {
//			info.setVersionName(versionName);
//		}
//		
//		if (versionCode != null) {
//			info.setVersionCode(versionCode);
//		}
//		
//		if (changeLog != null) {
//			info.setChangeLog(changeLog);
//		}
//		
//		if (isForced != null) {
//			info.setIsForced(isForced);
//		}
//		
//		if (isPublished != null) {
//			info.setIsPublished(isPublished);
//		}
//		
//		if (platformId != null) {
//			info.setPlatformId(platformId);
//		}
//		
//		if (downloadUrl != null) {
//			info.setDownloadUrl(downloadUrl);
//		}
//		
//		@SuppressWarnings("rawtypes")
//		ResponseBody resBody = new ResponseBody<AreaActivity>();
//		
//		int status = clientVersionService.update(info, resBody);
//		
//		return ResponseEntity.status(status).body(resBody); 
//	}
	
//	@ApiOperation(value = "获取版本信息列表", notes = "获取版本信息列表")
//	@RequestMapping(value = "", method = RequestMethod.GET)
//	public ResponseEntity<?> index() {
//		List<ClientVersion> list = new ArrayList<ClientVersion>();
//		
//		@SuppressWarnings("rawtypes")
//		ResponseBody resBody = new ResponseBody<List<ClientVersion>>();
//		
//		int status = clientVersionService.index(list, resBody);
//		
//		return ResponseEntity.status(status).body(resBody); 
//	}
}

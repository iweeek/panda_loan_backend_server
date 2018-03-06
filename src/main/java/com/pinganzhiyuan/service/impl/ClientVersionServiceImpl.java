package com.pinganzhiyuan.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pinganzhiyuan.mapper.ChannelMapper;
import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.service.ClientVersionService;
import com.pinganzhiyuan.util.ResponseBody;
import com.pinganzhiyuan.util.RetMsgTemplate;

@Service
public class ClientVersionServiceImpl implements com.pinganzhiyuan.service.ClientVersionService {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientVersionServiceImpl.class);
	
	String logMsg = "";

	@Autowired
	private ClientVersionMapper clientVersionMapper;
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public ClientVersion getLatestVersionInfo(byte platformId, long channelId, String packageName) {
        ClientVersionExample example = new ClientVersionExample();
        example.createCriteria().andPackageNameEqualTo(packageName).andPlatformIdEqualTo(platformId).andChannelIdEqualTo(channelId).andIsPublishedEqualTo(true);
        example.setOrderByClause("publish_time desc");
        
        List<ClientVersion> list = clientVersionMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public ClientVersion getVersion(byte platformId, long channelId, String packageName, Integer versionCode) {
        ClientVersionExample example = new ClientVersionExample();
        example.createCriteria().andPackageNameEqualTo(packageName).andPlatformIdEqualTo(platformId).andChannelIdEqualTo(channelId).andIsPublishedEqualTo(true)
        .andVersionCodeEqualTo(versionCode);
        example.setOrderByClause("publish_time desc");
        
        List<ClientVersion> list = clientVersionMapper.selectByExample(example);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }


	@Override
	public int create(ClientVersion clientVersion, ResponseBody resBody) {
		//判断数据是否重复
		ClientVersionExample example = new ClientVersionExample();
		example.createCriteria()
				.andNameEqualTo(clientVersion.getName())
				.andChannelIdEqualTo(clientVersion.getChannelId())
				.andVersionCodeEqualTo(clientVersion.getVersionCode());
		List<ClientVersion> sameRecordList = clientVersionMapper.selectByExample(example);
		if (sameRecordList.size() != 0) {
			logMsg = "已经存在有着相同 name、channelId、versionCode 的记录了";
			logger.error(logMsg);
			clientVersion.setId(sameRecordList.get(0).getId());
			resBody.statusMsg = logMsg;
			resBody.obj1 = clientVersion;
			// statusCode：409 产生冲突
			return HttpServletResponse.SC_CONFLICT;
		} else {
		    // 如果插入失败
			clientVersionMapper.insertSelective(clientVersion);
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			resBody.statusMsg = logMsg;
			resBody.obj1 = clientVersion;
			return HttpServletResponse.SC_CREATED;
		}
	}

	@Override
	public int update(ClientVersion clientVersion, ResponseBody resBody) {
		ClientVersionExample example = new ClientVersionExample();
		ClientVersion c = new ClientVersion();
		
		// 更新发布状态，不需要判断 title 是否存在
//		if (product.getTitle() == null) {
//	        productMapper.updateByPrimaryKeySelective(product);
//	        logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
//            logger.info(logMsg);
//            
//            resBody.obj1 = product;
//            resBody.statusMsg = logMsg; 
//            
//            return HttpServletResponse.SC_OK;
//		} else {
		    List<ClientVersion> list = null;
		    // 全量更新
//            example.createCriteria().andTitleEqualTo(product.getTitle()).andIdNotEqualTo(product.getId());
//            List<Product> list = productMapper.selectByExample(example);
//            // 更新需要判断该 title 是否已存在
//            if (list.size() > 0) {
//                logMsg = RetMsgTemplate.MSG_TEMPLATE_NAME_EXIST.replace("%s", product.getTitle());
//        			logger.error(logMsg);
//        			
//        			resBody.obj1 = list.get(0);
//        			resBody.statusMsg = logMsg; 
//        			
//        			return HttpServletResponse.SC_CONFLICT;
//        		} else {
        			example.clear();
        			example.createCriteria().andIdEqualTo(clientVersion.getId());
        			list = clientVersionMapper.selectByExample(example);
        			if (list.size() > 0) {
        				clientVersionMapper.updateByPrimaryKeySelective(clientVersion);
        				logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
        				logger.info(logMsg);
        				resBody.obj1 = clientVersion;
        				resBody.statusMsg = logMsg; 
        				return HttpServletResponse.SC_OK;
        			} else {
        				logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID.replace("%s", String.valueOf(clientVersion.getId()));
        				logger.error(logMsg);
        				resBody.obj1 = null;
        				resBody.statusMsg = logMsg;
        				return HttpServletResponse.SC_NOT_FOUND;
        			}
//        		}
//		}
		
	}

	@Override
	public int getAppNameByPackageName(String packageName, ResponseBody resBody) {
		List<ClientVersion> list = clientVersionMapper.selectByPackageName(packageName);
		
		if (list != null && list.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = list;
            return HttpServletResponse.SC_OK;
        }
        return HttpServletResponse.SC_NOT_FOUND;
	}

	@Override
	public int selectByAppName(String appName, ResponseBody resBody) {
		List<Channel> channels = channelMapper.selectByAppName(appName);
		List<ClientVersion> clientVersions = clientVersionMapper.selectByAppName(appName);
		if (channels != null)
		if (channels != null && channels.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj1 = channels;
        }
		if (clientVersions != null && clientVersions.size() != 0) {
            resBody.statusMsg = "查询成功";
            resBody.obj2 = clientVersions;
        }
		return HttpServletResponse.SC_OK;
	}
	
}

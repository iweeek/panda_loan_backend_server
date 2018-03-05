package com.pinganzhiyuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinganzhiyuan.dto.PageDTO;
import com.pinganzhiyuan.dto.ProductDTO;
import com.pinganzhiyuan.mapper.ColumnMapper;
import com.pinganzhiyuan.mapper.GuaranteeMapper;
import com.pinganzhiyuan.mapper.GuaranteeProductMappingMapper;
import com.pinganzhiyuan.mapper.ProductColumnMappingMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.Channel;
import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.model.Guarantee;
import com.pinganzhiyuan.model.GuaranteeProductMapping;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductColumnMapping;
import com.pinganzhiyuan.model.ProductColumnMappingExample;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.service.ChannelService;
import com.pinganzhiyuan.service.ProductColumnMappingService;
import com.pinganzhiyuan.service.ProductService;
import com.pinganzhiyuan.util.FileUtil;
import com.pinganzhiyuan.util.PathUtil;
import com.pinganzhiyuan.util.ResponseBody;
import com.pinganzhiyuan.util.RetMsgTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "渠道相关接口")
@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;
  
    private static final Logger logger = LoggerFactory.getLogger(ChannelController.class);
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "获取渠道列表", notes = "")
    @RequestMapping(value = "/channels", method = RequestMethod.GET, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> index(HttpServletResponse response, 
                                    @ApiParam("页码")
                                    @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                    @ApiParam("每页多少条")
                                    @RequestParam(required = false, defaultValue = "10") int pageSize
                                    ) {
        List<Channel> list = new ArrayList<Channel>();
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = channelService.index(list, resBody);
        
        return ResponseEntity.status(status).body(resBody); 
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "根据AppName获取Channel", notes = "")
    @RequestMapping(value = "/channelsByAppName", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> channelsByAppName(HttpServletResponse response, 
						    		 @ApiParam("AppName")
						    		 @RequestParam("appName") String appName
                                    ) {
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = channelService.indexChannelByAppName(appName, resBody);
        
        return ResponseEntity.status(status).body(resBody); 
    }
    
}

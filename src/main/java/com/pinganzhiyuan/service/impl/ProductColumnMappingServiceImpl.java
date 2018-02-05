package com.pinganzhiyuan.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinganzhiyuan.dto.PageDTO;
import com.pinganzhiyuan.dto.ProductDTO;
import com.pinganzhiyuan.mapper.GuaranteeMapper;
import com.pinganzhiyuan.mapper.GuaranteeProductMappingMapper;
import com.pinganzhiyuan.mapper.ProductColumnMappingMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.Guarantee;
import com.pinganzhiyuan.model.GuaranteeProductMapping;
import com.pinganzhiyuan.model.GuaranteeProductMappingExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductColumnMapping;
import com.pinganzhiyuan.model.ProductColumnMappingExample;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.service.ProductColumnMappingService;
import com.pinganzhiyuan.service.ProductService;
import com.pinganzhiyuan.util.ResponseBody;
import com.pinganzhiyuan.util.RetMsgTemplate;

/**
 * RunningActivity Service 实现类.
 *
 * @author x1ny
 * @date 2017年5月26日
 */
@Service
public class ProductColumnMappingServiceImpl implements ProductColumnMappingService {

	private static final Logger logger = LoggerFactory.getLogger(ProductColumnMappingServiceImpl.class);
	
	private String logMsg = "";
	@Autowired
    private ProductColumnMappingMapper productColumnMappingMapper;
	
    @Autowired
    private GuaranteeProductMappingMapper guaranteeProductMappingMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int create(ProductColumnMapping mapping) {
		
	    //判断数据是否重复
        ProductColumnMappingExample example = new ProductColumnMappingExample();
        // 如果插入失败
        int status = productColumnMappingMapper.insertSelective(mapping);
        
        if (status > 0) {
            System.out.println("插入ProductColumnMapping成功");
        }
       
		return HttpServletResponse.SC_CREATED;
	}

}

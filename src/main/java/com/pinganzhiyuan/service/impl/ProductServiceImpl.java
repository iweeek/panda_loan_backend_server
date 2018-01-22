package com.pinganzhiyuan.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.service.ProductService;
import com.pinganzhiyuan.util.PageEntity;
import com.pinganzhiyuan.util.ResponseBody;
import com.pinganzhiyuan.util.RetMsgTemplate;

/**
 * RunningActivity Service 实现类.
 *
 * @author x1ny
 * @date 2017年5月26日
 */
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private String logMsg = "";
	
	@Autowired
	private ProductMapper productMapper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int create(Product product, ResponseBody resBody) {
		//判断数据是否重复
		ProductExample example = new ProductExample();
		example.createCriteria().andTitleEqualTo(product.getTitle());
		List<Product> sameRecordList = productMapper.selectByExample(example);
		if (sameRecordList.size() != 0) {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_NAME_EXIST.replace("%s", product.getTitle());
			logger.error(logMsg);
			
			product.setId(sameRecordList.get(0).getId());
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = product;
			
			return HttpServletResponse.SC_CONFLICT;
		} else {
			productMapper.insertSelective(product);
			
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = product;
			
			return HttpServletResponse.SC_CREATED;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int show(Product product, ResponseBody resBody) {
	    ProductExample example = new ProductExample();
		example.createCriteria().andIdEqualTo(product.getId());
		List<Product> list = productMapper.selectByExample(example);
		if (list.size() > 0) {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = list.get(0);
			
			return HttpServletResponse.SC_OK;
		} else {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID;
			logger.error(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = product;
			
			return HttpServletResponse.SC_NOT_FOUND;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int update(Product product, ResponseBody resBody) {
		ProductExample example = new ProductExample();
		example.createCriteria().andTitleEqualTo(product.getTitle()).andIdNotEqualTo(product.getId());
		List<Product> list = productMapper.selectByExample(example);
		if (list.size() > 0) {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_NAME_EXIST.replace("%s", product.getTitle());
			logger.error(logMsg);
			
			resBody.obj1 = list.get(0);
			resBody.statusMsg = logMsg; 
			
			return HttpServletResponse.SC_CONFLICT;
		} else {
			example.clear();
			example.createCriteria().andIdEqualTo(product.getId());
			list = productMapper.selectByExample(example);
			if (list.size() > 0) {
				productMapper.updateByPrimaryKeySelective(product);
				logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
				logger.info(logMsg);
				
				resBody.obj1 = product;
				resBody.statusMsg = logMsg; 
				
				return HttpServletResponse.SC_OK;
			} else {
				logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID.replace("%s", String.valueOf(product.getId()));
				logger.error(logMsg);
				
				resBody.obj1 = null;
				resBody.statusMsg = logMsg;
				
				return HttpServletResponse.SC_NOT_FOUND;
			}
		
		}
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public int index(List<Product> productList, ResponseBody resBody) {
        ProductExample example = new ProductExample();
        example.createCriteria().andIdNotEqualTo(0l);
//        PageHelper.startPage(1, 10);
        List<Product> list = productMapper.selectByExample(example);
        if (list.size() > 0) {
            productList.addAll(list);
            
            logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
            logger.info(logMsg);
            
            resBody.statusMsg = logMsg;
            resBody.obj1 = productList;
            
            return HttpServletResponse.SC_OK;
        } else {
            logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID;
            logger.error(logMsg);
            
            resBody.statusMsg = logMsg;
            resBody.obj1 = productList;
            
            return HttpServletResponse.SC_NOT_FOUND;
        }
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int index(List<Product> productList, ResponseBody resBody, int pageNumber, int pageSize) {
		ProductExample example = new ProductExample();
		example.createCriteria().andIdNotEqualTo(0l);
		
		PageHelper.startPage(pageNumber, pageSize);
		productList = productMapper.selectByExample(example);
		
		if (productList.size() > 0) {
			
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
	        PageInfo<Product> pageInfo = new PageInfo<>(productList);
			resBody.obj1 = pageInfo;
			
			return HttpServletResponse.SC_OK;
		} else {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID;
			logger.error(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = productList;
			
			return HttpServletResponse.SC_NOT_FOUND;
		}
	}
}

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
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.Guarantee;
import com.pinganzhiyuan.model.GuaranteeProductMapping;
import com.pinganzhiyuan.model.GuaranteeProductMappingExample;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductExample;
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
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	private String logMsg = "";
	
	@Autowired
	private ProductMapper productMapper;
	
	@Autowired
	private GuaranteeMapper guaranteeMapper;
	
    @Autowired
    private GuaranteeProductMappingMapper guaranteeProductMappingMapper;

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
			// 更新 URL
			String url = product.getUrl();
			// http://119.23.12.36:8081/panda_loan/record?pid=16&redirect=https%3a%2f%2f
            Pattern p = Pattern.compile("pid=(.*?)&");//正则表达式，取; 和; 之间的字符串
            Matcher m = p.matcher(url);
            if (m.find()) {
                int i = url.indexOf("pid=" + m.group(1));
                String s1 = url.substring(0, i) + "pid=";
                System.out.println(s1);
                
                String s2 = url.substring(s1.length() + m.group(1).length());
                System.out.println(s2);
                
                StringBuilder sb = new StringBuilder();
                sb.append(s1 + product.getId() + s2);
                product.setUrl(sb.toString());
                productMapper.updateByPrimaryKeySelective(product);
            }
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = product;
			
			return HttpServletResponse.SC_CREATED;
		}
	}

	public static void main(String[] args) {
        
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int show(Product product, ResponseBody resBody) {
	    ProductExample example = new ProductExample();
		example.createCriteria().andIdEqualTo(product.getId());
		List<Product> list = productMapper.selectByExample(example);
		if (list.size() > 0) {
		    ProductDTO dto = new ProductDTO(list.get(0));
		    
		    List<Guarantee> guaranteeList = guaranteeMapper.selectByProductId(product.getId());
		    List<String> guarantees = new ArrayList<>();
		    for (Guarantee guarantee : guaranteeList) {
		        guarantees.add(guarantee.getCreditGuarantee());
		    }
		    dto.setGuarantees(guarantees);
		    
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = dto;
			
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
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//    @Override
//    public int index(List<Product> productList, ResponseBody resBody) {
//        ProductExample example = new ProductExample();
//        example.createCriteria().andIdNotEqualTo(0l);
////        PageHelper.startPage(1, 10);
//        List<Product> list = productMapper.selectByExample(example);
//        if (list.size() > 0) {
//            productList.addAll(list);
//            
//            logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
//            logger.info(logMsg);
//            
//            resBody.statusMsg = logMsg;
//            resBody.obj1 = productList;
//            
//            return HttpServletResponse.SC_OK;
//        } else {
//            logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID;
//            logger.error(logMsg);
//            
//            resBody.statusMsg = logMsg;
//            resBody.obj1 = productList;
//            
//            return HttpServletResponse.SC_NOT_FOUND;
//        }
//    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int index(List<Product> productList, ResponseBody resBody, int pageNumber, int pageSize) {
		ProductExample example = new ProductExample();
		example.createCriteria().andIdNotEqualTo(0l);
		
		PageHelper.startPage(pageNumber, pageSize);
		productList = productMapper.selectByExample(example);
		// 处理 URL，截取最后真正的链接
		for (Product product : productList) {
		    String decodeUrl = "";
	        Pattern p = Pattern.compile("redirect=(.*?)###");//正则表达式，取; 和; 之间的字符串  
	        Matcher m = p.matcher(product.getUrl() + "###");
	        if (m.find()) {
    	            try {
    	                decodeUrl = URLDecoder.decode(m.group(1), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
	        }
	        product.setUrl(decodeUrl);
		}
		
		if (productList.size() > 0) {
		    
		    List<ProductDTO> dtos = new ArrayList<>();
			for (Product product : productList) {
			    ProductDTO dto = new ProductDTO(product);
	            
	            List<Guarantee> guaranteeList = guaranteeMapper.selectByProductId(product.getId());
	            List<String> guarantees = new ArrayList<>();
	            for (Guarantee guarantee : guaranteeList) {
	                guarantees.add(guarantee.getCreditGuarantee());
	            }
	            dto.setGuarantees(guarantees);
	            dtos.add(dto);
			}
			PageInfo<Product> pageInfo = new PageInfo<>(productList);
			// 分页信息只有在startPage之后的
			PageDTO<List<ProductDTO>> pageDTO = new PageDTO<>();
			pageDTO.setDataCount((int) pageInfo.getTotal());
			pageDTO.setPageNumber(pageNumber);
			pageDTO.setPagesCount(pageInfo.getPages());
			pageDTO.setPageSize(pageSize);
			pageDTO.setData(dtos);
            
			logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
			logger.info(logMsg);
			
			resBody.statusMsg = logMsg;
//	        PageInfo<ProductDTO> pageInfo = new PageInfo<>(dtos);
			resBody.obj1 = pageDTO;
			
			return HttpServletResponse.SC_OK;
		} else {
			logMsg = RetMsgTemplate.MSG_TEMPLATE_NOT_FIND_BY_ID;
			logger.error(logMsg);
			
			resBody.statusMsg = logMsg;
			resBody.obj1 = productList;
			
			return HttpServletResponse.SC_NOT_FOUND;
		}
	}

    @Override
    public int index(List<Product> pointList, ResponseBody resBody) {
        // TODO Auto-generated method stub
        return 0;
    }
	
	
}

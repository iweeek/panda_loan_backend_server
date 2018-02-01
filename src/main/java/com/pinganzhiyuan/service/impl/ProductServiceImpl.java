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
//		if (sameRecordList.size() != 0) {
//			logMsg = RetMsgTemplate.MSG_TEMPLATE_NAME_EXIST.replace("%s", product.getTitle());
//			logger.error(logMsg);
//			
//			product.setId(sameRecordList.get(0).getId());
//			
//			resBody.statusMsg = logMsg;
//			resBody.obj1 = product;
//			
//			return HttpServletResponse.SC_CONFLICT;
//		} else {
		    // 如果插入失败
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
//		}
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
		Product p = new Product();
		
		// 更新发布状态，不需要判断 title 是否存在
		if (product.getTitle() == null) {
	        productMapper.updateByPrimaryKeySelective(product);
	        logMsg = RetMsgTemplate.MSG_TEMPLATE_OPERATION_OK;
            logger.info(logMsg);
            
            resBody.obj1 = product;
            resBody.statusMsg = logMsg; 
            
            return HttpServletResponse.SC_OK;
		} else {
		    List<Product> list = null;
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
        		
//        		}
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

	public List<Product> handleUrl(List<Product> productList) {
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
        return productList;
	}
	
	
	
    @Override
    public int index(List<Product> pointList, ResponseBody resBody) {
        return 0;
    }

    @Override
    public int resort(long touchId, long insertId, ResponseBody resBody) {
        ProductExample example = new ProductExample();
        example.setOrderByClause(" weight desc ");
        List<Product> originList = productMapper.selectByExample(example);
        int touchIndex = 0; // 表示插入位置的前一个位置索引。
        int insertIndex = 0; // 表示选中拖拽的元素索引。
        int oriWeight = 0; // 表示插入位置的前一个位置的权重。之后要赋值给待插入元素。
        boolean isFirst = false; // 表示插入位置是不是索引为 0。
        
        for (int i = 0; i < originList.size(); i++) {
            Product product = originList.get(i);
            if (product.getId() == insertId) {
                insertIndex = i;
                oriWeight = product.getWeight();
            }
            if (product.getId() == touchId) {
                touchIndex = i;
            }
        }
        System.out.println("after touchIndex: " + touchIndex);
        System.out.println("after insertIndex: " + insertIndex);
        
        // 插入位置索引为 0 的情况下，直接将第一个位置元素的权重加上 1 赋值给待插入元素
        if (insertId == -1) {
            originList.get(touchIndex).setWeight(originList.get(0).getWeight() + 1);
        } else {
            // 分两种情况，先判断是向上拖动，还是向下,于是下面是得到两个索引指向的产品
            Product touchProduct = originList.get(touchIndex);
            Product insertProduct = originList.get(insertIndex);
            
//            if (touchProduct.getWeight() < insertProduct.getWeight()) {
                System.out.println("向上拖动");
                // 初始权重小于末权重，向上拖动，遍历包括插入位置之前的所有产品，将其权重加上 1.
                for (int i = 0; i <= insertIndex; i++) {
                    originList.get(i).setWeight(originList.get(i).getWeight() + 1);
                }
                // 将待插入产品(touchIndex)的权重设置为插入位置的前一个位置元素的权重
                originList.get(touchIndex).setWeight(oriWeight);
                System.out.println("insertIndex: " + insertIndex + ", oriWeight: " + oriWeight);
//            } else if (touchProduct.getWeight() > insertProduct.getWeight()) {
//                System.out.println("向下拖动");
//                // 初始权重大于末权重，向下拖动，遍历包括插入位置之前的所有产品，将其权重加上 1.
//                for (int i = 0; i <= insertIndex; i++) {
//                    originList.get(i).setWeight(originList.get(i).getWeight() + 1);
//                }
//                // 将待插入产品(touchIndex)的权重设置为插入位置的前一个位置元素的权重
//                originList.get(touchIndex).setWeight(oriWeight);
//            }
        }
        
        // 遍历更新
        for (int i = 0; i < originList.size(); i++) {
            productMapper.updateByPrimaryKeySelective(originList.get(i));
        }
        
        return HttpServletResponse.SC_OK;
    }

}

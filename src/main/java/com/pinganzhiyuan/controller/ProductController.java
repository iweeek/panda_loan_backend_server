package com.pinganzhiyuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.pinganzhiyuan.mapper.AppClientMapper;
import com.pinganzhiyuan.mapper.ColumnMapper;
import com.pinganzhiyuan.mapper.GuaranteeMapper;
import com.pinganzhiyuan.mapper.GuaranteeProductMappingMapper;
import com.pinganzhiyuan.mapper.ProductColumnMappingMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.model.AppClient;
import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.model.Guarantee;
import com.pinganzhiyuan.model.GuaranteeProductMapping;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductColumnMapping;
import com.pinganzhiyuan.model.ProductColumnMappingExample;
import com.pinganzhiyuan.model.ProductExample;
import com.pinganzhiyuan.service.AppClientService;
import com.pinganzhiyuan.service.ProductColumnMappingService;
import com.pinganzhiyuan.service.ProductService;
import com.pinganzhiyuan.util.FileUtil;
import com.pinganzhiyuan.util.PathUtil;
import com.pinganzhiyuan.util.ResponseBody;
import com.pinganzhiyuan.util.RetMsgTemplate;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "产品相关接口")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired 
    private ProductColumnMappingService productColumnMappingService;
    
    @Autowired 
    private ProductColumnMappingMapper productColumnMappingMapper;
    
    @Autowired 
    private ColumnMapper columnMapper;
    
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private AppClientMapper appClientMapper;
    
    @Autowired
    private GuaranteeMapper guaranteeMapper;
    
    @Autowired
    private GuaranteeProductMappingMapper guaranteeProductMappingMapper;
    
    
   
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    /**
     * 创建的时有默认值
     * @return
     */
    @RequiresPermissions("product:write")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建一个产品", notes = "使用POST来创建一个新的产品，由服务端来分配创建一个新资源")
    @RequestMapping(value = "/products", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> create(
                            @ApiParam("主标题")
                            @RequestParam String title,
                            @ApiParam("对应客户端“新”标签")
                            @RequestParam Boolean isNew,
                            @ApiParam("是否置顶")
                            @RequestParam Boolean isTop,
                            @ApiParam("对应客户端第一行的标签")
                            @RequestParam(name = "firstTags") String[] firstTags,
                            @ApiParam("产品的描述")
                            @RequestParam(required = false, defaultValue = "") String description,
                            @ApiParam("是否发布 0表示下线；1表示上线。")
                            @RequestParam Integer isPublished,
                            @ApiParam("图片地址")
                            @RequestParam String imgUrl,
                            @ApiParam("导向的资方地址")
                            @RequestParam String url,
                            @ApiParam("贷款金额下限")
                            @RequestParam Integer minAmount,
                            @ApiParam("贷款金额上限")
                            @RequestParam Integer maxAmount,
                            @ApiParam("贷款期限下限")
                            @RequestParam Integer minTerm,
                            @ApiParam("贷款期限上限")
                            @RequestParam Integer maxTerm,
                            @ApiParam("资方名称")
                            @RequestParam String lenderName,
                            @ApiParam("产品日利率")
                            @RequestParam String dayRate,
                            @ApiParam("借款资格")
                            @RequestParam(name = "guarantees", required = false) String[] guarantees,
                            @ApiParam("借款资格")
                            @RequestParam(name = "columnKeys", required = false) String[] columnKeys,
                            @ApiParam("发布到的APP名字列表")
                            @RequestParam(name = "appNames", required = false) String[] appNames,
                            @ApiParam("发布到的渠道列表")
                            @RequestParam(name = "channelNames", required = false) String[] channelNames
                            ) {
        
        Product product = new Product();
        
        if (title != null) {
            product.setTitle(title);
        } else {
            product.setTitle("");
        }
        if (isNew != null) {
            product.setIsNew(isNew);
        } else {
            product.setIsNew(false);
        }
        
        if (isTop != null && isTop) {
            productService.bringToTop(product);
        } else {
            product.setWeight(0);
        }
        
        // 拼接标签
        StringBuilder tagBuilder = new StringBuilder();
        if (firstTags != null && firstTags.length > 0) {
            for (String tag : firstTags) {
                tagBuilder.append(tag + "|");
            }
            // 去掉最后一个 “|”
            product.setFirstTags(tagBuilder.substring(0, tagBuilder.toString().length() - 1));
        } else {
            product.setFirstTags("");
        }
        
        List<String> secondTagList = new ArrayList<>();
        
        if (maxAmount != null) {
            String secondTag = "";
            if ((maxAmount / 10000) >= 1) {
                secondTag = "最高" + maxAmount / 10000 + "万元";
            } else {
                secondTag = "最高" + maxAmount + "元";
            }
            secondTagList.add(secondTag);
        }
        
        if (dayRate != null) {
            secondTagList.add("日利率" + dayRate + "%起");
        }
        if (secondTagList.size() > 0) {
            if (secondTagList.size() == 2) {
                product.setSecondTags(secondTagList.get(0) + "|" + secondTagList.get(1));
            } else {
                product.setSecondTags(secondTagList.get(0));
            }
        } else {
            product.setSecondTags("");
        }
        
        if (description != null) {
            product.setDescription(description);
        } else {
            product.setDescription("");
        }
        
        product.setIsPublished(isPublished);
        // 创建时指定为发布状态
        if (isPublished == 1) {
            product.setPublishTime(new Date());
        }
        
        if (imgUrl != null) {
            product.setImgUrl(imgUrl);
        } else {
            product.setImgUrl("");
        }
        
        // 导向地址
        // http://119.23.12.36:8081/panda_loan/record?pid=1&redirect=http%3A%2F%2Fh.sinaif.com%2FloginCommon%3FcodeKey%3D1002_s2828233_CP001
        // TODO https://api.pinganzhiyuan.com/panda_loan/record?pid=143&redirect=https%3A%2F%2Fat.umeng.com%2FH9DWTz
        String origin = PathUtil.getInstance().PRODUCTURL;
        System.out.println("新增产品 product url: " + origin);
        if (url != null) {
            try {
                String encode = URLEncoder.encode(url, "UTF-8");
                String finalUrl = origin + encode;
                product.setUrl(finalUrl);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                product.setUrl("");
            }
        } else {
            product.setUrl("");
        }
        
        product.setLightTitle("");
        product.setEdu("");
        
        if (minAmount != null) {
            product.setMinAmount(minAmount);
        } else {
            product.setMinAmount(0);
        }
        
        if (maxAmount != null) {
            product.setMaxAmount(maxAmount);
        } else {
            product.setMaxAmount(0);
        }
        
        if (minTerm != null) {
            product.setMinTerm(minTerm);
        } else {
            product.setMinTerm(0);
        }
        
        if (maxTerm != null) {
            product.setMaxTerm(maxTerm);
        } else {
            product.setMaxTerm(0);
        }
        
        product.setLowInterest((double) 0);
        product.setHighInterest((double) 0);
        product.setCreditAuth("");
        
        if (lenderName != null) {
            product.setLenderName(lenderName);
        } else {
            product.setLenderName("");
        }
     
        product.setLenderDesc("");
        product.setActiveCaptchaUrl("");
        product.setRegInterfaceUrl("");
        
        product.setDisplayType((byte) 0);
        product.setChargeModeId((long) 0);
        
//        if (applyTimes != null) {
            product.setApplyTimes(0);
//        }
//        if (loanWaitTime != null) {
            product.setLoanWaitTime(0);
//        }
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        // 更新发布到的APP以及渠道的 aid 到 product 表的 ap|p_id
        // 暂时还没有找到如何在mybatis中，in的 数组 传递形式。这个要研究一下
        if (appNames != null && channelNames != null) {
            StringBuilder appBuilder = new StringBuilder();
            appBuilder.append("select a.id as id, a.name, \n" + 
                    "a.package_name, a.channel_id, b.`name` as channel_name \n" + 
                    "from app_client a join channel b on a.channel_id = b.id\n" +
                    " where a.name in (");
            
            for (String appName : appNames) {
                appBuilder.append("'" + appName + "',");
            }
            appBuilder = appBuilder.delete(
                    appBuilder.toString().length() - 1, appBuilder.toString().length()).append(") ");
            // 要确保至少有一个渠道被选中
            appBuilder.append(" and b.`name` in (");
            for (String channelName : channelNames) {
                appBuilder.append("'" + channelName + "',");
            }
            appBuilder = appBuilder.delete(
                    appBuilder.toString().length() - 1, appBuilder.toString().length()).append(") ");
            
            // 执行
            List<AppClient> publishAppAndChannel = 
                    appClientMapper.selectByAppNamesAndChannelNames(appBuilder.toString());
            StringBuilder appClientIds  = new StringBuilder();
            for (AppClient client : publishAppAndChannel) {
                appClientIds.append(client.getId() + ",");
            }
            appClientIds = appClientIds.delete(appClientIds.toString().length() - 1, appClientIds.toString().length());
            
            product.setAppClientIds(appClientIds.toString());
        }
        
        // 插入成功，product.getId() 取到插入后的Id
        int status = productService.create(product, resBody);
        
        // 借款资格
        if (guarantees != null && guarantees.length != 0) {
            List<Guarantee> guaranteeList = guaranteeMapper.selectByExample(null);
            for (String lq : guarantees) {
                for (Guarantee guarantee : guaranteeList) {
                    if (guarantee.getCreditGuarantee().equals(lq)) {
                        // 找到匹配的项，插入到映射表中
//                        guarantee.getId()
                        GuaranteeProductMapping g = new GuaranteeProductMapping();
                        g.setGuaranteeId(guarantee.getId());
                        g.setProductId(product.getId());
                        int insertSelective = guaranteeProductMappingMapper.insertSelective(g);
                        if (insertSelective > 0) {
                            System.out.println("插入GuaranteeProductMapping成功");
                        }
                    }
                }
                
            }
        }
        
        ProductColumnMapping mapping = new ProductColumnMapping();
        // 更新产品栏位映射表
        if (columnKeys != null && columnKeys.length != 0) {
            for (String key : columnKeys) {
                mapping.setColumnKey(key);
                mapping.setProductId(product.getId());
                productColumnMappingService.create(mapping);
            }
        }
        
        return ResponseEntity.status(status).body(resBody);
    }
    
    /* 移动到 FileUtil 中
    @RequestMapping(value = { "/uploadImage" }, method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@ApiParam("图片") @RequestParam MultipartFile image) {
        String imagePath = "";
        String imageUrl = "";
        try {
            imagePath = FileUtil.uploadFile(PathUtil.IMG_STORAGE_PATH, image);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        imageUrl = PathUtil.getInstance().ORIGIN + File.separator + imagePath;
//        imageUrl = File.separator + imagePath;
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(imageUrl);
    }
    */
    
    @RequiresPermissions("product:read")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "获取一个产品", notes = "根据Id来获取一个产品")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> show(
                            @PathVariable long id
                            ) {
        Product product = new Product();
        product.setId(id);
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.show(product, resBody);
        
        return ResponseEntity.status(status).body(resBody);
    }
    
    @RequiresPermissions("product:write")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "更新一个产品", notes = "根据Id来更新一个产品")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> update(
                                    @ApiParam("id")
                                    @PathVariable long id,
                                    @ApiParam("主标题")
                                    @RequestParam(required = false) String title,
                                    @ApiParam("对应客户端“新”标签")
                                    @RequestParam(required = false) Boolean isNew,
                                    @ApiParam("对应客户端第一行的标签")
                                    @RequestParam(name = "firstTags", required = false) String[] firstTags,
                                    @ApiParam("产品的描述")
                                    @RequestParam(required = false) String description,
                                    @ApiParam("是否发布 0表示下线；1表示上线。")
                                    @RequestParam(required = false) Integer isPublished,
                                    @ApiParam("图片地址")
                                    @RequestParam(required = false) String imgUrl,
                                    @ApiParam("导向的资方地址")
                                    @RequestParam(required = false) String url,
                                    @ApiParam("贷款金额下限")
                                    @RequestParam(required = false) Integer minAmount,
                                    @ApiParam("贷款金额上限")
                                    @RequestParam(required = false) Integer maxAmount,
                                    @ApiParam("贷款期限下限")
                                    @RequestParam(required = false) Integer minTerm,
                                    @ApiParam("贷款期限上限")
                                    @RequestParam(required = false) Integer maxTerm,
                                    @ApiParam("资方名称")
                                    @RequestParam(required = false) String lenderName,
                                    @ApiParam("产品日利率")
                                    @RequestParam(required = false) String dayRate,
                                    @ApiParam("借款资格")
                                    @RequestParam(name = "guarantees", required = false) String[] guarantees,
                                    @ApiParam("权重")
                                    @RequestParam(required = false) String weight,
                                    @ApiParam("是否置顶")
                                    @RequestParam(required = false) Boolean isTop,
                                    @ApiParam("借款资格")
                                    @RequestParam(name = "columnKeys", required = false) String[] columnKeys,
                                    @ApiParam("发布到的APP名字列表")
                                    @RequestParam(name = "appNames", required = false) String[] appNames,
                                    @ApiParam("发布到的渠道列表")
                                    @RequestParam(name = "channelNames", required = false) String[] channelNames
                            ) {
        Product product = new Product();
        
        product.setId(id);
        
        if (title != null) {
            product.setTitle(title);
        }
        if (isNew != null) {
            product.setIsNew(isNew);
        }
        
        // 拼接标签
        StringBuilder sb = new StringBuilder();
        
        if (firstTags != null && firstTags.length > 0) {
            for (String tag : firstTags) {
                sb.append(tag + "|");
            }
            product.setFirstTags(sb.substring(0, sb.toString().length() - 1));
        }
        
        List<String> secondTagList = new ArrayList<>();
        
        if (maxAmount != null) {
            String secondTag = "";
            if ((maxAmount / 10000) >= 1) {
                secondTag = "最高" + maxAmount / 10000 + "万元";
            } else {
                secondTag = "最高" + maxAmount + "元";
            }
            secondTagList.add(secondTag);
        }
        
        if (dayRate != null) {
            secondTagList.add("日利率" + dayRate + "%起");
        }
        if (secondTagList.size() > 0) {
            if (secondTagList.size() == 2) {
                product.setSecondTags(secondTagList.get(0) + "|" + secondTagList.get(1));
            } else {
                product.setSecondTags(secondTagList.get(0));
            }
        }
        
//        if (secondTags != null) {
//            product.setSecondTags(secondTags);
//        }
        if (description != null) {
            product.setDescription(description);
        }
        
        if (isPublished != null) {
            product.setIsPublished(isPublished);
            if (isPublished == 1) {
                product.setPublishTime(new Date());
            } else if (isPublished == 2) {
                product.setUnpublishTime(new Date());
            }
        }
        
        if (imgUrl != null) {
            product.setImgUrl(imgUrl);
        }
        
        // 导向地址
//         http://119.23.12.36:8081/panda_loan/record?pid=1&redirect=http%3A%2F%2Fh.sinaif.com%2FloginCommon%3FcodeKey%3D1002_s2828233_CP001
//        String origin = "http://119.23.12.36:8081/panda_loan/record?pid=0&redirect=";
        // url 是不允许更新的。
//        String origin = "https://api.pinganzhiyuan.com/panda_loan/record?pid=0&redirect=";
//        if (url != null) {
//            try {
//                String encode = URLEncoder.encode(url, "UTF-8");
//                String finalUrl = origin + encode;
//                product.setUrl(finalUrl);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                product.setUrl("");
//            }
//        }
        
        // 判断是否置顶
        if (isTop != null && isTop) {
            productService.bringToTop(product);
        }
        
//        product.setLightTitle("");
//        product.setEdu("");
        
        if (minAmount != null) {
            product.setMinAmount(minAmount);
        }
        
        if (maxAmount != null) {
            product.setMaxAmount(maxAmount);
        }
        
        if (minTerm != null) {
            product.setMinTerm(minTerm);
        }
        
        if (maxTerm != null) {
            product.setMaxTerm(maxTerm);
        }
//        if (lowInterest != null) {
//            product.setLowInterest((double) 0);
//        }
//        if (highInterest != null) {
//            product.setHighInterest((double) 0);
//        }
        
//        product.setCreditAuth("");
        
        if (lenderName != null) {
            product.setLenderName(lenderName);
        }
     
//        product.setLenderDesc("");
//        product.setActiveCaptchaUrl("");
//        product.setRegInterfaceUrl("");
        
//        product.setDisplayType((byte) 0);
//        product.setChargeModeId((long) 0);
        
//        if (applyTimes != null) {
//            product.setApplyTimes(0);
//        }
//        if (loanWaitTime != null) {
//            product.setLoanWaitTime(0);
//        }
            
         // 更新发布到的APP以及渠道的 aid 到 product 表的 ap|p_id
        // 暂时还没有找到如何在mybatis中，in的 数组 传递形式。这个要研究一下
        if (appNames != null && channelNames != null) {
            StringBuilder appBuilder = new StringBuilder();
            appBuilder.append("select a.id as id, a.name, \n" + 
                    "a.package_name, a.channel_id, b.`name` as channel_name \n" + 
                    "from app_client a join channel b on a.channel_id = b.id\n" +
                    " where a.name in (");
            
            for (String appName : appNames) {
                appBuilder.append("'" + appName + "',");
            }
            appBuilder = appBuilder.delete(
                    appBuilder.toString().length() - 1, appBuilder.toString().length()).append(") ");
            // 要确保至少有一个渠道被选中
            appBuilder.append(" and b.`name` in (");
            for (String channelName : channelNames) {
                appBuilder.append("'" + channelName + "',");
            }
            appBuilder = appBuilder.delete(
                    appBuilder.toString().length() - 1, appBuilder.toString().length()).append(") ");
            
            // 执行
            List<AppClient> publishAppAndChannel = 
                    appClientMapper.selectByAppNamesAndChannelNames(appBuilder.toString());
            StringBuilder appClientIds  = new StringBuilder();
            for (AppClient client : publishAppAndChannel) {
                appClientIds.append(client.getId() + ",");
            }
            appClientIds = appClientIds.delete(appClientIds.toString().length() - 1, appClientIds.toString().length());
            
            product.setAppClientIds(appClientIds.toString());
        }
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.update(product, resBody);
        
        if (guarantees != null && guarantees.length != 0) {
            // 先删除所有的映射记录
            List<Guarantee> deleteList = guaranteeMapper.selectByProductId(product.getId());
            for (Guarantee guarantee : deleteList) {
                guaranteeProductMappingMapper.deleteByPrimaryKey(guarantee.getMappingId());
                System.out.println("删除成功+++   " + guarantee.getMappingId());
            }
            
            // 重新插入
            List<Guarantee> guaranteeList = guaranteeMapper.selectByExample(null);
            for (String lq : guarantees) {
                for (Guarantee guarantee : guaranteeList) {
                    if (guarantee.getCreditGuarantee().equals(lq)) {
                        // 找到匹配的项，插入到映射表中
                        GuaranteeProductMapping g = new GuaranteeProductMapping();
                        g.setGuaranteeId(guarantee.getId());
                        g.setProductId(product.getId());
                        int insertSelective = guaranteeProductMappingMapper.insertSelective(g);
                        if (insertSelective > 0) {
                            System.out.println("插入GuaranteeProductMapping成功");
                        }
                    }
                }
            }
        }
        if (columnKeys != null && columnKeys.length != 0) {
            // 先删除所有的映射记录
            ProductColumnMappingExample example = new ProductColumnMappingExample();
            example.createCriteria().andProductIdEqualTo(product.getId());
            List<ProductColumnMapping> deleteList = productColumnMappingMapper.selectByExample(example);
            for (ProductColumnMapping mapping : deleteList) {
                productColumnMappingMapper.deleteByPrimaryKey(mapping.getId());
                System.out.println("删除产品栏位映射成功+++   " + mapping.getId());
            }
            
            ProductColumnMapping mapping = new ProductColumnMapping();
            // 重新插入
            for (String key : columnKeys) {
                mapping.setColumnKey(key);
                mapping.setProductId(product.getId());
                productColumnMappingService.create(mapping);
            }
        }
        
        return ResponseEntity.status(status).body(resBody); 
    }
    
    @RequiresPermissions("product:read")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "获取产品列表", notes = "")
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> index(HttpServletResponse response, 
                                    @ApiParam("页码")
                                    @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                    @ApiParam("每页多少条")
                                    @RequestParam(required = false, defaultValue = "30") int pageSize
                                    ) {
        List<Product> list = new ArrayList<Product>();
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.index(list, resBody, pageNumber, pageSize);
        
        return ResponseEntity.status(status).body(resBody); 
    }

    
    /**
     * 筛选指定产品
     * @return 
     * obj1：返回带有分页信息的过滤之后的产品列表
     * obj2：返回带有最高权重值的产品ID
     */
    @RequiresPermissions("product:read")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "筛选产品", notes = "根据指定的条件进行筛选")
    @RequestMapping(value = "/products/query", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> screen(
                                    @ApiParam("主标题")
                                    @RequestParam(required = false) String title,
                                    @ApiParam("资方名称")
                                    @RequestParam(required = false) String lenderName,
                                    @ApiParam("发布状态 0表示下线；1表示上线。")
                                    @RequestParam(required = false) Integer isPublished,
                                    @ApiParam("发布时间(时间戳)")
                                    @RequestParam(required = false) Long publishTime,
                                    @ApiParam("另一个发布时间(时间戳)")
                                    @RequestParam(required = false) Long anotherPublishTime,
                                    @ApiParam("贷款金额下限")
                                    @RequestParam(required = false) Integer minAmount,
                                    @ApiParam("贷款金额上限")
                                    @RequestParam(required = false) Integer maxAmount,
                                    @ApiParam("贷款期限下限")
                                    @RequestParam(required = false) Integer minTerm,
                                    @ApiParam("贷款期限上限")
                                    @RequestParam(required = false) Integer maxTerm,
                                    @ApiParam("借款资格资质")
                                    @RequestParam(name = "guarantees", required =false) String[] guarantees,
                                    @ApiParam("页码")
                                    @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                    @ApiParam("每页多少条")
                                    @RequestParam(required = false, defaultValue = "30") int pageSize
                                    ) {
        List<Product> list = null;
        
        // 查询字符串
        StringBuilder sb = new StringBuilder();
        // 拼接的 where 字句
        StringBuilder whereCause = new StringBuilder();
        
        // 先判断是否需要关联其他表，
//        if (guarantees != null && guarantees.length != 0) {
//        if (guarantees != null && guarantees.length != 0) {
//            sb.append(obj)
//        }
            sb.append("SELECT p.*, gm.guarantee_id, g.credit_guarantee " + 
                      "from product as p join guarantee_product_mapping as gm on p.id = gm.product_id " + 
                      "join guarantee as g on g.id = gm.guarantee_id " +
                      "where ");
            sb.append("1 = 1 ");
            
            if (guarantees != null && guarantees.length != 0) {
                sb.append("and (");
                for (String guarantee : guarantees) {
                    sb.append("credit_guarantee = '" + guarantee + "' and ");
                }
    //            if (guarantees.length != 1) {
                    sb = sb.delete(sb.toString().length() - 4, sb.toString().length()).append(") ");
    //            }
            }
            
            if (title != null) {
                whereCause.append("and title like '%" + title + "%' ");
            }
            
            if (lenderName != null) {
                whereCause.append("and lender_name like '%" + lenderName + "%' ");
            }
            
            if (isPublished != null) {
                whereCause.append("and is_published = " + isPublished + " ");
            }
            
            if (minAmount != null) {
                whereCause.append("and min_amount <= " + minAmount + " ");
            }
            
            if (maxAmount != null) {
                whereCause.append("and max_amount >= " + maxAmount + " ");
            }
            
            if (minTerm != null) {
                whereCause.append("and min_term <= " + minTerm + " ");
            }
            
            if (maxTerm != null) {
                whereCause.append("and max_term >= " + maxTerm + " ");
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            long st = Long.parseLong(publishTime);
            if (publishTime != null) {
                Date date = new Date(publishTime);
                String format = sdf.format(date);
                System.out.println("startTime: " + format);
                whereCause.append("and publish_time >= date('" + format +"') ");
            }
            
            if (anotherPublishTime != null) {
                Date date = new Date(anotherPublishTime);
                String format = sdf.format(date);
                System.out.println("endTime: " + format);
                whereCause.append("and publish_time <= date('" + format +"') ");
            }
            
            whereCause.append("group by id");
            sb.append(whereCause);
            
            if (guarantees == null || guarantees.length == 0) {
                sb.insert(0, "(");
                sb.append(") union (select *, 0 as guarantee_id, 0 as credit_guarantee " + 
                        "from product " + 
                        "where id not in (select a.id from product a join guarantee_product_mapping b " +
                        "where a.id = b.product_id GROUP BY a.id)" + whereCause.toString() + ") ");
//                sb.append("union ")
            }
            sb.append(" ORDER BY weight DESC ");
            System.out.println(sb.toString());
            PageHelper.startPage(pageNumber, pageSize);
            list = productMapper.selectByQueryString(sb.toString());
            
//        }
        
        ResponseBody resBody = new ResponseBody<List<Product>>();
        
        // 分页
        PageDTO<List<ProductDTO>> pageDTO = filterProduct(list, pageSize, pageSize);
        
        // obj1：返回带有分页信息的过滤之后的产品列表
        resBody.obj1 = pageDTO;
        
        ProductExample example = new ProductExample();
        example.setOrderByClause(" weight desc ");
        List<Product> weightList = productMapper.selectByExample(example);
        if (weightList.size() != 0) {
            // obj2：返回带有最高权重值的产品ID
            resBody.obj2 = weightList.get(0).getId();
        }
        
            
        return ResponseEntity.status(HttpServletResponse.SC_OK).body(resBody); 
    }
    
    /**
     * 将传入的集合转成目标类型后，加入分页数据
     * @param list 原始的集合
     * @param pageNumber 页码
     * @param pageSize 每页记录数
     * @return
     */
    public PageDTO<List<ProductDTO>> filterProduct(List<Product> list, int pageNumber, int pageSize) {
        List<ProductDTO> dtos = new ArrayList<>();
        for (Product product : list) {
            ProductDTO dto = new ProductDTO(product);
            // TODOs
            List<Guarantee> guaranteeList = guaranteeMapper.selectByProductId(product.getId());
            List<String> g = new ArrayList<>();
            for (Guarantee guarantee : guaranteeList) {
                g.add(guarantee.getCreditGuarantee());
            }
            dto.setGuarantees(g);
            
            // 获取栏位
            ProductColumnMappingExample example = new ProductColumnMappingExample();
            example.createCriteria().andProductIdEqualTo(product.getId());
            // 关联product_column_mapping 与 column表
            List<Column> columns = columnMapper.selectByProductId(product.getId());
            if (columns.size() > 0) {
                dto.setColumns(columns);
            }
            
            // 发布到的APP及渠道
            Set<String> appNames = new HashSet<String>();
            Set<String> channelNames = new HashSet<String>();
            String appClientIdString = product.getAppClientIds();
            if (appClientIdString != null) {
                List<AppClient> appClients = appClientMapper.selectSingleProductWithChannel(appClientIdString);
                if (appClients != null && appClients.size() != 0) {
                    for (AppClient appClient : appClients) {
                        appNames.add(appClient.getName());
                        channelNames.add(appClient.getChannelName());
                    }
                    System.out.println("appNames:" + appNames);
                    System.out.println("channelNames:" + channelNames);
                    dto.setAppNames(appNames);
                    dto.setChannelNames(channelNames);
                }
            }
            
            dtos.add(dto);
        }
        
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        PageDTO<List<ProductDTO>> pageDTO = new PageDTO<>();
        pageDTO.setDataCount((int) pageInfo.getTotal());
        pageDTO.setPageNumber(pageNumber);
        pageDTO.setPagesCount(pageInfo.getPages());
        pageDTO.setPageSize(pageSize);
        pageDTO.setData(dtos);
        
        return pageDTO;
    }
    
    @RequiresPermissions("product:write")
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "排序产品", notes = "客户端传上来一个Id")
    @RequestMapping(value = "/products/sort", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> sort(
                            @ApiParam("Id")
                            @RequestParam(required = false) Long touchId,
                            @ApiParam("排序Id")
                            @RequestParam(required = false) Long insertId
                            ) {
        System.out.println("before touchId: " + touchId);
        System.out.println("before insertId: " + insertId);
        ResponseBody resBody = new ResponseBody<Product>();
        int status = productService.resort(touchId, insertId, resBody);
        return ResponseEntity.status(status).body(resBody);
    }
    
    
   
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long st = Long.parseLong("1516867278000");
        Date date = new Date(st);
        String format = sdf.format(date);
        System.out.println(format);
    }
}

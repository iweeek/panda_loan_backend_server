package com.pinganzhiyuan.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.pinganzhiyuan.mapper.GuaranteeMapper;
import com.pinganzhiyuan.mapper.GuaranteeProductMappingMapper;
import com.pinganzhiyuan.model.Guarantee;
import com.pinganzhiyuan.model.GuaranteeProductMapping;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.service.ProductService;
import com.pinganzhiyuan.util.FileUtil;
import com.pinganzhiyuan.util.PathUtil;
import com.pinganzhiyuan.util.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "产品相关接口")
@RestController
//@RequestMapping(value="/areaSports",produces="application/json;charset=UTF-8")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @Autowired
    private GuaranteeMapper guaranteeMapper;
    
    @Autowired
    private GuaranteeProductMappingMapper guaranteeProductMappingMapper;
   
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "创建一个产品", notes = "使用POST来创建一个新的产品，由服务端来分配创建一个新资源")
    @RequestMapping(value = "/products", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> create(
                            @ApiParam("主标题")
                            @RequestParam String title,
                            @ApiParam("对应客户端“新”标签")
                            @RequestParam Boolean isNew,
                            @ApiParam("对应客户端第一行的标签")
                            @RequestParam(name = "firstTags") String[] firstTags,
//                            @ApiParam("对应客户端第二行的标签")
//                            @RequestParam(required = false, defaultValue = "") String secondTags,
                            @ApiParam("产品的描述")
                            @RequestParam(required = false, defaultValue = "") String description,
                            @ApiParam("是否发布 0表示下线；1表示上线。")
                            @RequestParam Integer isPublished,
                            @ApiParam("图片地址")
                            @RequestParam String imgUrl,
                            @ApiParam("导向的资方地址")
                            @RequestParam String url,
//                            @ApiParam("权重，用来对产品排序")
//                            @RequestParam int weight,
//                            @ApiParam("小标签")
//                            @RequestParam String lightTitle,
//                            @ApiParam("参考的原来的数据库的贷款金额上下限的字段，原本是字符串，现在拆成两个字段，这个字段不再使用")
//                            @RequestParam(required = false, defaultValue = "") String edu,
                            @ApiParam("贷款金额下限")
                            @RequestParam Integer minAmount,
                            @ApiParam("贷款金额上限")
                            @RequestParam Integer maxAmount,
                            @ApiParam("贷款期限下限")
                            @RequestParam Integer minTerm,
                            @ApiParam("贷款期限上限")
                            @RequestParam Integer maxTerm,
//                            @ApiParam("利率下限")
//                            @RequestParam Double lowInterest,
//                            @ApiParam("利率上限")
//                            @RequestParam Double highInterest,
//                            @ApiParam("信用资质")
//                            @RequestParam(required = false, defaultValue = "") String creditAuth,
                            @ApiParam("资方名称")
                            @RequestParam String lenderName,
                            @ApiParam("产品日利率")
                            @RequestParam String dayRate,
                            @ApiParam("借款资格")
                            @RequestParam(name = "guarantees") String[] guarantees
//                            @ApiParam("资方描述")
//                            @RequestParam String lenderDesc,
//                            @ApiParam("资方landing page页面中的获取验证码的地址")
//                            @RequestParam String activeCaptchaUrl,
//                            @ApiParam("这个是指资方landing page页面中的注册的链接")
//                            @RequestParam String regInterfaceUrl,
//                            @ApiParam("发布时间(时间戳)")
//                            @RequestParam Long publishTime,
//                            @ApiParam("下线时间(时间戳)")
//                            @RequestParam Long unpublishTime,
//                            @ApiParam("展示类型")
//                            @RequestParam(required = false, defaultValue = "0") int displayType,
//                            @ApiParam("对资方收费方式")
//                            @RequestParam(required = false, defaultValue = "0") Long chargeModeId,
//                            @ApiParam("申请次数")
//                            @RequestParam Integer applyTimes,
//                            @ApiParam("放款等待时间")
//                            @RequestParam Integer loanWaitTime
                            ) {
        
        Product product = new Product();
        
        if (title != null) {
            product.setTitle(title);
        }
        if (isNew != null) {
            product.setIsNew(isNew);
        }
        
        // 拼接标签
        StringBuilder sb = new StringBuilder();
        if (firstTags.length > 0) {
            for (String tag : firstTags) {
                sb.append(tag + "|");
            }
            product.setFirstTags(sb.substring(0, sb.toString().length() - 1));
        } else {
            product.setFirstTags("");
        }
        
        List<String> secondTagList = new ArrayList<>();
        
        if (maxAmount != null) {
            secondTagList.add("最高" + maxAmount / 10000 + "万元");
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
        
//        if (secondTags != null) {
//            product.setSecondTags(secondTags);
//        }
        if (description != null) {
            product.setDescription(description);
        }
        
        product.setIsPublished(isPublished);
        if (imgUrl != null) {
            product.setImgUrl(imgUrl);
        }
        
        // 导向地址
        // http://119.23.12.36:8081/panda_loan/record?pid=1&redirect=http%3A%2F%2Fh.sinaif.com%2FloginCommon%3FcodeKey%3D1002_s2828233_CP001
        String origin = "http://119.23.12.36:8081/panda_loan/record?pid=0&redirect=";
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
        
        product.setWeight(0);
        product.setLightTitle("");
        product.setEdu("");
        
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
            product.setLowInterest((double) 0);
//        }
//        if (highInterest != null) {
            product.setHighInterest((double) 0);
//        }
        
        product.setCreditAuth("");
        
        if (lenderName != null) {
            product.setLenderName(lenderName);
        }
     
        product.setLenderDesc("");
        product.setActiveCaptchaUrl("");
        product.setRegInterfaceUrl("");
        
//        product.setPublishTime(new Date());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date;
//        try {
//            date = format.parse("0000-00-00 00:00:00");
//            product.setUnpublishTime(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//       
        
        product.setDisplayType((byte) 0);
        product.setChargeModeId((long) 0);
        
//        if (applyTimes != null) {
            product.setApplyTimes(0);
//        }
//        if (loanWaitTime != null) {
            product.setLoanWaitTime(0);
//        }
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.create(product, resBody);
        
        if (guarantees != null) {
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
        
        return ResponseEntity.status(status).body(resBody);
    }
    
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
        imageUrl = PathUtil.getInstance().ORIGIN + File.separator + PathUtil.IMG_FOLDER_PATH + imagePath;
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(imageUrl);
    }
    
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
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "更新一个产品", notes = "根据Id来更新一个产品")
    @RequestMapping(value = "/products/{id}", method = RequestMethod.POST, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> update(
                                    @ApiParam("id")
                                    @PathVariable long id,
                                    @ApiParam("主标题")
                                    @RequestParam String title,
                                    @ApiParam("对应客户端“新”标签")
                                    @RequestParam Boolean isNew,
                                    @ApiParam("对应客户端第一行的标签")
                                    @RequestParam(name = "firstTags") String[] firstTags,
//                                    @ApiParam("对应客户端第二行的标签")
//                                    @RequestParam(required = false, defaultValue = "") String secondTags,
                                    @ApiParam("产品的描述")
                                    @RequestParam(required = false, defaultValue = "") String description,
                                    @ApiParam("是否发布 0表示下线；1表示上线。")
                                    @RequestParam Integer isPublished,
                                    @ApiParam("图片地址")
                                    @RequestParam String imgUrl,
                                    @ApiParam("导向的资方地址")
                                    @RequestParam String url,
//                                    @ApiParam("权重，用来对产品排序")
//                                    @RequestParam int weight,
//                                    @ApiParam("小标签")
//                                    @RequestParam String lightTitle,
//                                    @ApiParam("参考的原来的数据库的贷款金额上下限的字段，原本是字符串，现在拆成两个字段，这个字段不再使用")
//                                    @RequestParam(required = false, defaultValue = "") String edu,
                                    @ApiParam("贷款金额下限")
                                    @RequestParam Integer minAmount,
                                    @ApiParam("贷款金额上限")
                                    @RequestParam Integer maxAmount,
                                    @ApiParam("贷款期限下限")
                                    @RequestParam Integer minTerm,
                                    @ApiParam("贷款期限上限")
                                    @RequestParam Integer maxTerm,
//                                    @ApiParam("利率下限")
//                                    @RequestParam Double lowInterest,
//                                    @ApiParam("利率上限")
//                                    @RequestParam Double highInterest,
//                                    @ApiParam("信用资质")
//                                    @RequestParam(required = false, defaultValue = "") String creditAuth,
                                    @ApiParam("资方名称")
                                    @RequestParam String lenderName,
                                    @ApiParam("产品日利率")
                                    @RequestParam String dayRate,
                                    @ApiParam("借款资格")
                                    @RequestParam(name = "guarantees") String[] guarantees
//                                    @ApiParam("资方描述")
//                                    @RequestParam String lenderDesc,
//                                    @ApiParam("资方landing page页面中的获取验证码的地址")
//                                    @RequestParam String activeCaptchaUrl,
//                                    @ApiParam("这个是指资方landing page页面中的注册的链接")
//                                    @RequestParam String regInterfaceUrl,
//                                    @ApiParam("发布时间(时间戳)")
//                                    @RequestParam Long publishTime,
//                                    @ApiParam("下线时间(时间戳)")
//                                    @RequestParam Long unpublishTime,
//                                    @ApiParam("展示类型")
//                                    @RequestParam(required = false, defaultValue = "0") int displayType,
//                                    @ApiParam("对资方收费方式")
//                                    @RequestParam(required = false, defaultValue = "0") Long chargeModeId,
//                                    @ApiParam("申请次数")
//                                    @RequestParam Integer applyTimes,
//                                    @ApiParam("放款等待时间")
//                                    @RequestParam Integer loanWaitTime
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
        if (firstTags.length > 0) {
            for (String tag : firstTags) {
                sb.append(tag + "|");
            }
            product.setFirstTags(sb.substring(0, sb.toString().length() - 1));
        } else {
            product.setFirstTags("");
        }
        
        List<String> secondTagList = new ArrayList<>();
        
        if (maxAmount != null) {
            secondTagList.add("最高" + maxAmount / 10000 + "万元");
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
        
//        if (secondTags != null) {
//            product.setSecondTags(secondTags);
//        }
        if (description != null) {
            product.setDescription(description);
        }
        
        product.setIsPublished(isPublished);
        if (imgUrl != null) {
            product.setImgUrl(imgUrl);
        }
        
        // 导向地址
        // http://119.23.12.36:8081/panda_loan/record?pid=1&redirect=http%3A%2F%2Fh.sinaif.com%2FloginCommon%3FcodeKey%3D1002_s2828233_CP001
        String origin = "http://119.23.12.36:8081/panda_loan/record?pid=0&redirect=";
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
        
        product.setWeight(0);
        product.setLightTitle("");
        product.setEdu("");
        
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
            product.setLowInterest((double) 0);
//        }
//        if (highInterest != null) {
            product.setHighInterest((double) 0);
//        }
        
        product.setCreditAuth("");
        
        if (lenderName != null) {
            product.setLenderName(lenderName);
        }
     
        product.setLenderDesc("");
        product.setActiveCaptchaUrl("");
        product.setRegInterfaceUrl("");
        
//        product.setPublishTime(new Date());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date;
//        try {
//            date = format.parse("0000-00-00 00:00:00");
//            product.setUnpublishTime(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//       
        
        product.setDisplayType((byte) 0);
        product.setChargeModeId((long) 0);
        
//        if (applyTimes != null) {
            product.setApplyTimes(0);
//        }
//        if (loanWaitTime != null) {
            product.setLoanWaitTime(0);
//        }
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.update(product, resBody);
        
        if (guarantees != null) {
            // 先删除所有的映射记录
            List<Guarantee> deleteList = guaranteeMapper.selectByProductId(product.getId());
            for (Guarantee guarantee : deleteList) {
//                guarantees.add(guarantee.getCreditGuarantee());
                guaranteeMapper.deleteByPrimaryKey(guarantee.getId());
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
        
        return ResponseEntity.status(status).body(resBody); 
    }
    
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "获取产品列表", notes = "")
    @RequestMapping(value = "/products", method = RequestMethod.GET, produces="application/json;charset=UTF-8") 
    public ResponseEntity<?> index(HttpServletResponse response, 
                                    @ApiParam("页码")
                                    @RequestParam(required = false, defaultValue = "1") int pageNumber,
                                    @ApiParam("每页多少条")
                                    @RequestParam(required = false, defaultValue = "10") int pageSize
                                    ) {
        List<Product> list = new ArrayList<Product>();
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.index(list, resBody, pageNumber, pageSize);
        
        return ResponseEntity.status(status).body(resBody); 
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("://", "UTF-8");
        System.out.println(encode);
    }
    
}

package com.pinganzhiyuan.controller;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.service.ProductService;
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
                            @RequestParam String firstTags,
                            @ApiParam("对应客户端第二行的标签")
                            @RequestParam String secondTags,
                            @ApiParam("客户端第三行的描述")
                            @RequestParam String description,
                            @ApiParam("是否发布 0表示下线；1表示上线。")
                            @RequestParam Boolean isPublished,
                            @ApiParam("图片地址")
                            @RequestParam String imgUrl,
                            @ApiParam("导向的资方地址")
                            @RequestParam String url,
                            @ApiParam("权重，用来对产品排序")
                            @RequestParam int weight,
                            @ApiParam("小标签")
                            @RequestParam String lightTitle,
                            @ApiParam("参考的原来的数据库的贷款金额上下限的字段，原本是字符串，现在拆成两个字段，这个字段不再使用")
                            @RequestParam(required = false, defaultValue = "") String edu,
                            @ApiParam("贷款金额下限")
                            @RequestParam int minAmount,
                            @ApiParam("贷款金额上限")
                            @RequestParam int maxAmount,
                            @ApiParam("贷款期限下限")
                            @RequestParam int minTerm,
                            @ApiParam("贷款期限上限")
                            @RequestParam int maxTerm,
                            @ApiParam("利率下限")
                            @RequestParam Double lowInterest,
                            @ApiParam("利率上限")
                            @RequestParam Double highInterest,
                            @ApiParam("信用资质")
                            @RequestParam(required = false, defaultValue = "") String creditAuth,
                            @ApiParam("资方名称")
                            @RequestParam String lenderName,
                            @ApiParam("资方描述")
                            @RequestParam String lenderDesc,
                            @ApiParam("资方landing page页面中的获取验证码的地址")
                            @RequestParam String activeCaptchaUrl,
                            @ApiParam("这个是指资方landing page页面中的注册的链接")
                            @RequestParam String regInterfaceUrl,
//                            @ApiParam("发布时间(时间戳)")
//                            @RequestParam Long publishTime,
//                            @ApiParam("下线时间(时间戳)")
//                            @RequestParam Long unpublishTime,
                            @ApiParam("展示类型")
                            @RequestParam(required = false, defaultValue = "0") int displayType,
                            @ApiParam("对资方收费方式")
                            @RequestParam(required = false, defaultValue = "0") Long chargeModeId,
                            @ApiParam("申请次数")
                            @RequestParam int applyTimes,
                            @ApiParam("放款等待时间")
                            @RequestParam int loanWaitTime
                            ) {
        Product product = new Product();
        
        product.setTitle(title);
        product.setIsNew(isNew);
        product.setFirstTags(firstTags);
        product.setSecondTags(secondTags);
        product.setDescription(description);
        product.setIsPublished(false);
        product.setImgUrl(imgUrl);
        product.setUrl(url);
        product.setWeight(weight);
        product.setLightTitle(lightTitle);
        product.setEdu(edu);
        product.setMinAmount(minAmount);
        product.setMaxAmount(maxAmount);
        product.setMinTerm(minTerm);
        product.setMaxTerm(maxTerm);
        product.setLowInterest(lowInterest);
        product.setHighInterest(highInterest);
        product.setCreditAuth(creditAuth);
        product.setLenderName(lenderName);
        product.setLenderDesc(lenderDesc);
        product.setActiveCaptchaUrl(activeCaptchaUrl);
        product.setRegInterfaceUrl(regInterfaceUrl);
        
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
        
        product.setDisplayType((byte) displayType);
        product.setChargeModeId(chargeModeId);
        product.setApplyTimes(applyTimes);
        product.setLoanWaitTime(loanWaitTime);
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.create(product, resBody);
        
        return ResponseEntity.status(status).body(resBody);
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
                            @RequestParam String firstTags,
                            @ApiParam("对应客户端第二行的标签")
                            @RequestParam String secondTags,
                            @ApiParam("客户端第三行的描述")
                            @RequestParam String description,
                            @ApiParam("是否发布 0表示下线；1表示上线。")
                            @RequestParam Boolean isPublished,
                            @ApiParam("图片地址")
                            @RequestParam String imgUrl,
                            @ApiParam("导向的资方地址")
                            @RequestParam String url,
                            @ApiParam("权重，用来对产品排序")
                            @RequestParam int weight,
                            @ApiParam("小标签")
                            @RequestParam String lightTitle,
                            @ApiParam("参考的原来的数据库的贷款金额上下限的字段，原本是字符串，现在拆成两个字段，这个字段不再使用")
                            @RequestParam(required = false, defaultValue = "") String edu,
                            @ApiParam("贷款金额下限")
                            @RequestParam int minAmount,
                            @ApiParam("贷款金额上限")
                            @RequestParam int maxAmount,
                            @ApiParam("贷款期限下限")
                            @RequestParam int minTerm,
                            @ApiParam("贷款期限上限")
                            @RequestParam int maxTerm,
                            @ApiParam("利率下限")
                            @RequestParam Double lowInterest,
                            @ApiParam("利率上限")
                            @RequestParam Double highInterest,
                            @ApiParam("信用资质")
                            @RequestParam(required = false, defaultValue = "") String creditAuth,
                            @ApiParam("资方名称")
                            @RequestParam String lenderName,
                            @ApiParam("资方描述")
                            @RequestParam String lenderDesc,
                            @ApiParam("资方landing page页面中的获取验证码的地址")
                            @RequestParam String activeCaptchaUrl,
                            @ApiParam("这个是指资方landing page页面中的注册的链接")
                            @RequestParam String regInterfaceUrl,
//                            @ApiParam("发布时间(时间戳)")
//                            @RequestParam Long publishTime,
//                            @ApiParam("下线时间(时间戳)")
//                            @RequestParam Long unpublishTime,
                            @ApiParam("展示类型")
                            @RequestParam(required = false, defaultValue = "0") int displayType,
                            @ApiParam("对资方收费方式")
                            @RequestParam(required = false, defaultValue = "0") Long chargeModeId,
                            @ApiParam("申请次数")
                            @RequestParam int applyTimes,
                            @ApiParam("放款等待时间")
                            @RequestParam int loanWaitTime
                            ) {
        Product product = new Product();
        
        product.setId(id);
        product.setTitle(title);
        product.setIsNew(isNew);
        product.setFirstTags(firstTags);
        product.setSecondTags(secondTags);
        product.setDescription(description);
        product.setIsPublished(isPublished);
        product.setImgUrl(imgUrl);
        product.setUrl(url);
        product.setWeight(weight);
        product.setLightTitle(lightTitle);
        product.setEdu(edu);
        product.setMinAmount(minAmount);
        product.setMaxAmount(maxAmount);
        product.setMinTerm(minTerm);
        product.setMaxTerm(maxTerm);
        product.setLowInterest(lowInterest);
        product.setHighInterest(highInterest);
        product.setCreditAuth(creditAuth);
        product.setLenderName(lenderName);
        product.setLenderDesc(lenderDesc);
        product.setActiveCaptchaUrl(activeCaptchaUrl);
        product.setRegInterfaceUrl(regInterfaceUrl);
//        product.setPublishTime(new Date(publishTime * 1000));
//        product.setUnpublishTime(new Date(unpublishTime * 1000));
        product.setDisplayType((byte) displayType);
        product.setChargeModeId(chargeModeId);
        product.setApplyTimes(applyTimes);
        product.setLoanWaitTime(loanWaitTime);
        
        ResponseBody resBody = new ResponseBody<Product>();
        
        int status = productService.update(product, resBody);
        
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
    
}

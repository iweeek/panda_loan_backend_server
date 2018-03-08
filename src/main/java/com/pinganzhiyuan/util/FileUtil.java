package com.pinganzhiyuan.util;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.validator.PublicClassValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.pinganzhiyuan.model.ClientVersion;

import io.swagger.annotations.ApiParam;
import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.Icon;
import net.dongliu.apk.parser.bean.UseFeature;

@Controller
public class FileUtil {
	
	private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI4JxkqvnFS082";
    private static String accessKeySecret = "d06xSgJDhcbLMzUPBkHA2hG36AfjQA";
    private static String bucketName = "resource-apk";
    private static String originApkName = "";
	
	static 	public String uploadFile(String path, MultipartFile mFile) throws IOException {

	    String fileName = UUID.randomUUID().toString();
	    String oriFileName = mFile.getOriginalFilename();
	    if (oriFileName != null) {
    	    String ext = oriFileName.substring(oriFileName.lastIndexOf("."), oriFileName.length());
    	    ext = ext.toLowerCase();
    	    
    	    fileName = fileName + ext;
	    }
	    
	    
		String filePath = path + File.separator + fileName;
		File file = new File(filePath);

		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdir();
		}

		// 不用先创建文件
//		boolean isCreated = false;
//		if (!file.exists()) {
//			isCreated = file.createNewFile();
//            if (isCreated) {
//                System.out.println(isCreated);
//            }
//            Runtime.getRuntime().exec("chmod o+rx " + file.getAbsolutePath()); 
//			
//		}

		mFile.transferTo(file);
		
		System.out.println("file.getAbsolutePath(): " + file.getAbsolutePath());
        Runtime.getRuntime().exec("chmod o+rx " + file.getAbsolutePath()); 
		
		return fileName;
	}
	
	/**
	 * 保存图片到服务器，返回图片地址。
	 * @param image
	 * @return
	 */
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
	
	public static String saveCaptcha(String path, BufferedImage image) throws IOException {

        String fileName = UUID.randomUUID().toString();
        fileName = fileName + ".jpeg";
        
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        ImageIO.write(image, "jpeg", file);
//        mFile.transferTo(file);
        
        return fileName;
    }
	
	/**
	 * 保存文件到服务器，返回 ClientVersion 信息。
	 * @param file
	 * @return
	 */
    @RequestMapping(value = { "/uploadFile" }, method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@ApiParam("文件") @RequestParam(name = "file") MultipartFile multipartFile) {
    		ClientVersion clientVersion = null;
    		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    		
    		System.out.println("Getting Started with OSS SDK for Java\n");
    		
    		try {
	    		boolean doesBucketExist = ossClient.doesBucketExist(bucketName);
	    		
	    		/*
	         * List the buckets in your account
	         */
	        System.out.println("Listing buckets");
	        
	        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
	        listBucketsRequest.setMaxKeys(500);
	        
	        for (Bucket bucket : ossClient.listBuckets()) {
	            System.out.println(" - " + bucket.getName());
	        }
	        System.out.println();
	        File file = multipartToFile(multipartFile);
	        clientVersion = (ClientVersion) resolveApkFile(file);
	        
	        /*
	         * Upload an object to your bucket
	         */
	        System.out.println("Uploading a new object to OSS from a file\n");
	        ossClient.putObject(new PutObjectRequest(bucketName, originApkName, file));
	        ossClient.setObjectAcl(bucketName, originApkName, CannedAccessControlList.PublicRead);
	        ossClient.setObjectAcl(bucketName, originApkName, CannedAccessControlList.Default);
	        
	        /*
             * Delete an object
             */
//            System.out.println("Deleting an object\n");
//            ossClient.deleteObject(bucketName, originApkName);
    		} catch (OSSException oe) {
                System.out.println("Caught an OSSException, which means your request made it to OSS, "
                        + "but was rejected with an error response for some reason.");
                System.out.println("Error Message: " + oe.getErrorCode());
                System.out.println("Error Code:       " + oe.getErrorCode());
                System.out.println("Request ID:      " + oe.getRequestId());
                System.out.println("Host ID:           " + oe.getHostId());
            } catch (ClientException ce) {
                System.out.println("Caught an ClientException, which means the client encountered "
                        + "a serious internal problem while trying to communicate with OSS, "
                        + "such as not being able to access the network.");
                System.out.println("Error Message: " + ce.getMessage());
            } finally {
                ossClient.shutdown();
            }
    		
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(clientVersion);
    }
    
    /**
     * 解析 APK 文件
     * @param file
     * @return
     */
    public Object resolveApkFile(File file) {
    		ClientVersion clientVersion = new ClientVersion();
//    		File file = new File(filePath);
    	  	// 解析 APK 包
        try (ApkFile apkFile = new ApkFile(file)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            clientVersion.setName(apkMeta.getLabel());
            clientVersion.setVersionCode(apkMeta.getVersionCode().intValue());
            clientVersion.setVersionName(apkMeta.getVersionName());
            clientVersion.setPackageName(apkMeta.getPackageName());
            String[] split = apkMeta.getPackageName().split("\\.");
            originApkName = split[split.length - 1] + "/" + apkMeta.getVersionName() + "/" + file.getName();
            http://download.pinganzhiyuan.com/loanwaiter/1.0.0/app-mi-release.apk
            	clientVersion.setDownloadUrl("http://download.pinganzhiyuan.com/" + originApkName);
            System.out.println(apkMeta.getLabel());
            System.out.println(apkMeta.getPackageName());
            System.out.println(apkMeta.getVersionCode());
            System.out.println(apkMeta.getPackageName());
            
            String manifestXml = apkFile.getManifestXml();
            
            resolveAndroidManifestFile(manifestXml, clientVersion);
            Icon iconFile = apkFile.getIconFile();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(iconFile.getData());
            String[] iconFileSplit = iconFile.getPath().split("\\/");
            String filePath = PathUtil.FILE_STORAGE_PATH + iconFileSplit[0] + File.separator + iconFileSplit[1] + File.separator;
            String fileName = iconFileSplit[2];
            File icon = FileUtil.byteToFile(iconFile.getData(), filePath, fileName);
            MultipartFile byteToMultiPartFile = byteToMultiPartFile(icon, iconFile.getData());
            ResponseEntity<?> uploadImage = uploadImage(byteToMultiPartFile);
            String logoUrl = (String) uploadImage.getBody();
            clientVersion.setLogoUrl(logoUrl);
            
//            File icon = new File(PathUtil.FILE_STORAGE_PATH, iconFile.getPath());
//            FileOutputStream fileOutputStream = new FileOutputStream(icon);
//            fileOutputStream.write(iconFile);
//            FileWriter writer = new FileWriter(icon);
            
            System.out.println(manifestXml);
        } catch (IOException e) {
			e.printStackTrace();
		}
        return clientVersion;
    }
    
    private void resolveAndroidManifestFile(String manifestXml, ClientVersion clientVersion) {
    	
    		SAXReader reader = new SAXReader();
        try {
        		ByteArrayInputStream stream = new ByteArrayInputStream(manifestXml.getBytes());  
            // 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
            Document document = reader.read(stream);
            // 通过document对象获取根节点bookstore
            Element bookStore = document.getRootElement();
            // 通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            // 遍历迭代器，获取根节点中的信息（书籍）
            while (it.hasNext()) {
//             System.out.println("=====开始遍历某一本书=====");
             Element book = (Element) it.next();
             // 获取book的属性名以及 属性值
             List<Attribute> bookAttrs = book.attributes();
             for (Attribute attr : bookAttrs) {
              System.out.println("属性名：" + attr.getName() + "--属性值：" + attr.getValue());
             }
             //解析子节点的信息
             Iterator itt = book.elementIterator();
             while (itt.hasNext()) {
              Element bookChild = (Element) itt.next();
              if (bookChild != null) {
            	  		if (bookChild.attribute(0) != null && bookChild.attribute(1) != null) {
            	  			String txt = "name: " + bookChild.attribute(0).getValue() + 
            	  					", value: " + bookChild.attribute(1).getValue(); 
	            	  		System.out.println(txt);
	            	  		if (bookChild.attribute(0).getValue().equals("JPUSH_CHANNEL")) {
	            	  			clientVersion.setChannelId(Long.parseLong(bookChild.attribute(1).getValue()));
	            	  		}
            	  		}
              }
              
              System.out.println("节点名：" + bookChild.getName() + "--节点值：" + bookChild.getStringValue());
             }
//             System.out.println("=====结束遍历某一本书=====");
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
	}

	/** 
     * MultipartFile 转换成File 
     *  
     * @param multfile 原文件类型 
     * @return File 
     * @throws IOException 
     */  
    private File multipartToFile(MultipartFile multfile) {  
        CommonsMultipartFile cf = (CommonsMultipartFile)multfile;   
        //这个myfile是MultipartFile的  
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();  
        
        File file = fi.getStoreLocation();  
        File newNameFile = new File(file.getParent() + File.separator + fi.getName());
        file.renameTo(newNameFile);
        
//        file.renameTo(fi.getName());
        //手动创建临时文件  
//        if(file.length() < CommonConstants.MIN_FILE_SIZE){  
//            File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +   
//                    file.getName());  
//            multfile.transferTo(tmpFile);  
//            return tmpFile;  
//        }  
        return newNameFile;  
    }  
    
    /** 
     * 根据byte数组，生成文件(临时)
     */  
    public static File byteToFile(byte[] bfile, String filePath,String fileName) {  
        BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try {  
            File dir = new File(filePath + File.separator);  
            if(!dir.exists() /*&& dir.isDirectory()*/){//判断文件目录是否存在  
                dir.mkdirs();  
            }  
            file = new File(filePath + fileName);  
            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(bfile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bos != null) {  
                try {  
                    bos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
            if (fos != null) {  
                try {  
                    fos.close();  
                } catch (IOException e1) {  
                    e1.printStackTrace();  
                }  
            }  
        }  
        return file;
    }
    
    public MultipartFile byteToMultiPartFile(File file, byte[] content) {
	    	String name = file.getName();
	    	String originalFileName = file.getName();
	    	String contentType = "image/png";
	    	MultipartFile result = new MockMultipartFile(name,
	    	                     originalFileName, contentType, content);
	    	return result;
    }
}

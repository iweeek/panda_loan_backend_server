package com.pinganzhiyuan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.annotations.ApiParam;

public class FileUtil {
	
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
}

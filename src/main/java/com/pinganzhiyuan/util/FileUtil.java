package com.pinganzhiyuan.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

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

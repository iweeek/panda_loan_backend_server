package com.pinganzhiyuan.temp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.Locale;

import org.springframework.web.bind.annotation.RestController;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.ListBucketsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectAcl;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PutObjectRequest;
import com.pinganzhiyuan.util.FileUtil;
import com.pinganzhiyuan.util.PathUtil;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.ApkSigner;
import net.dongliu.apk.parser.bean.ApkV2Signer;
import net.dongliu.apk.parser.bean.DexClass;
import net.dongliu.apk.parser.bean.Icon;
import net.dongliu.apk.parser.bean.UseFeature;

/**
 * This sample demonstrates how to get started with basic requests to Aliyun OSS 
 * using the OSS SDK for Java.
 */
@RestController
public class GetStartedSample {
    
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAI4JxkqvnFS082";
    private static String accessKeySecret = "d06xSgJDhcbLMzUPBkHA2hG36AfjQA";
    private static String bucketName = "resource-apk";
    private static String key = "<key>";
    
    public static void main(String[] args) throws IOException {
    	
    		File file2 = new File("/Users/nijun/Desktop/app-cdn-release.apk");
    		// Apk info
    		try (ApkFile apkFile = new ApkFile(file2)) {
            ApkMeta apkMeta = apkFile.getApkMeta();
            System.out.println(apkMeta.getLabel());
            System.out.println(apkMeta.getPackageName());
            System.out.println(apkMeta.getVersionCode());
            for (UseFeature feature : apkMeta.getUsesFeatures()) {
                System.out.println(feature.getName());
            }
            Icon iconFile = apkFile.getIconFile();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(iconFile.getData());
            FileUtil.byteToFile(iconFile.getData(), PathUtil.FILE_STORAGE_PATH, "a.png");
            File file = new File(PathUtil.FILE_STORAGE_PATH, "a.png");
        }
    		// Get binary xml and manifest xml file
    		try (ApkFile apkFile = new ApkFile(file2)) {
    		    String manifestXml = apkFile.getManifestXml();
    		    System.out.println(manifestXml);
    		    String xml = apkFile.transBinaryXml("AndroidManifest.xml");
    		    System.out.println(xml);
    		}
    		// Get dex classes
    		try(ApkFile apkFile = new ApkFile(file2)) {
    		    DexClass[] classes = apkFile.getDexClasses();
    		    for (DexClass dexClass : classes) {
    		        System.out.println(dexClass);
    		    }
    		}
    		// Get Apk Sign info
    		try(ApkFile apkFile = new ApkFile(file2)) {
    		    try {
					List<ApkSigner> signers = apkFile.getApkSingers();
					List<ApkV2Signer> v2signers = apkFile.getApkV2Singers(); // apk v2 signers
				} catch (CertificateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} // apk v1 signers
    		}
    		
    		try (ApkFile apkFile = new ApkFile(file2)) {
    		    apkFile.setPreferredLocale(Locale.SIMPLIFIED_CHINESE);
    		    ApkMeta apkMeta = apkFile.getApkMeta();
    		}
    	
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        
        System.out.println("Getting Started with OSS SDK for Java\n");
        
        try {

        	boolean doesBucketExist = ossClient.doesBucketExist(bucketName);
            /*
             * Determine whether the bucket exists
             */
            if (!ossClient.doesBucketExist(bucketName)) {
                /*
                 * Create a new OSS bucket
                 */
                System.out.println("Creating bucket " + bucketName + "\n");
                ossClient.createBucket(bucketName);
                CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }

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
            
            /*
             * Upload an object to your bucket
             */
            System.out.println("Uploading a new object to OSS from a file\n");
            ossClient.putObject(new PutObjectRequest(bucketName, key, createSampleFile()));
//            ossClient.putObject("<bucketName>", keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            /*
             * Determine whether an object residents in your bucket
             */
            boolean exists = ossClient.doesObjectExist(bucketName, key);
            System.out.println("Does object " + bucketName + " exist? " + exists + "\n");
            /*
             * Upload an object to your bucket
             */
            System.out.println("Uploading a new object to OSS from a file\n");
            File file = new File("/Users/nijun/Desktop/ElCapitan2.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            
            ossClient.putObject(new PutObjectRequest(bucketName, key, fileInputStream));
            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
            ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.Default);
            
            ObjectAcl objectAcl = ossClient.getObjectAcl(bucketName, key);
            System.out.println("ACL:" + objectAcl.getPermission().toString());
            
            /*
             * Download an object from your bucket
             */
            System.out.println("Downloading an object");
            OSSObject object = ossClient.getObject(bucketName, key);
            System.out.println("Content-Type: "  + object.getObjectMetadata().getContentType());
            displayTextInputStream(object.getObjectContent());

            /*
             * List objects in your bucket by prefix
             */
            System.out.println("Listing objects");
            ObjectListing objectListing = ossClient.listObjects(bucketName, "My");
            for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
                System.out.println(" - " + objectSummary.getKey() + "  " +
                                   "(size = " + objectSummary.getSize() + ")");
            }
            System.out.println();

            /*
             * Delete an object
             */
            System.out.println("Deleting an object\n");
            ossClient.deleteObject(bucketName, key);
            
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
            /*
             * Do not forget to shut down the client finally to release all allocated resources.
             */
            ossClient.shutdown();
        }
    }
    
    private static File createSampleFile() throws IOException {
        File file = File.createTempFile("oss-java-sdk-", ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write("abcdefghijklmnopqrstuvwxyz\n");
        writer.write("0123456789011234567890\n");
        writer.close();

        return file;
    }

    private static void displayTextInputStream(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        while (true) {
            String line = reader.readLine();
            if (line == null) break;

            System.out.println("    " + line);
        }
        System.out.println();
        
        reader.close();
    }

}

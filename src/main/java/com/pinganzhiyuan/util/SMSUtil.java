package com.pinganzhiyuan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.pinganzhiyuan.model.SMSLog;

public class SMSUtil {
    
    public static String sendSMS(String str) throws UnsupportedEncodingException {  
        String url = "http://smssh1.253.com/msg/send/json";
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        HttpPost httpPost = new HttpPost(url);  
        StringEntity entity;
        entity = new StringEntity(str, ContentType.APPLICATION_JSON);
        
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        
        try {
            CloseableHttpResponse response = httpclient.execute(httpPost);
            StringBuilder sb = new StringBuilder();
            try {
                BufferedReader reader = 
                       new BufferedReader(new InputStreamReader(response.getEntity().getContent()), 65728);
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) { 
                e.printStackTrace(); 
            } catch (Exception e) { 
                e.printStackTrace(); 
            }

            System.out.println("finalResult " + sb.toString());
            
            httpclient.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
    }  
}

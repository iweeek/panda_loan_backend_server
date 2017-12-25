package com.pinganzhiyuan.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.pinganzhiyuan.model.IdVerification;
import com.pinganzhiyuan.model.SMSLog;

public class IdVerifUtil {
    
    final static String token = "a5cb8b9ae9db4025b9d324fa95592755";
    
    public static String sendIdVerf(String name, String id, String type) throws UnsupportedEncodingException {  
        String result= "";
        String url = "https://api.creditm.cn/tags/v2/idCardAuth?name={name}&idCard={idCard}&type={type}";
        
        url = url.replace("{name}", name);
        url = url.replace("{idCard}", id);
        url = url.replace("{type}", type);
        
        CloseableHttpClient httpclient = HttpClients.createDefault(); 
        HttpGet httpGet = new HttpGet(url);  
        
        String auth = "token " + token;
        httpGet.setHeader("Authorization", auth);
        
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();  
                result = EntityUtils.toString(httpEntity);
                result.replaceAll("\r", "");
                
                return result;
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } 
        
        return null;
    }  
}

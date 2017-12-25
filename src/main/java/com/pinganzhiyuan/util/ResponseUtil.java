package com.pinganzhiyuan.util;

import java.util.HashMap;

import com.google.gson.Gson;

/**
* @ClassName: ResponseUtil
* @Description: 处理请求响应的工具类
* @author x1ny
* @date 2017年5月9日
*/
public class ResponseUtil {
	private static Gson gson = new Gson();
	
	/**
	* @author x1ny
	* @date 2017年5月9日
	* @Description: 将返回信息处理成标准的返回格式
	* @param errMessages - 错误信息数组
	* @param obj - 返回的对象
	* @return
	* @throws
	*/
	public static String standardResponse(String[] errMessages, Object obj) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int errNumber = errMessages == null ? 0 : errMessages.length;
		result.put("errNumber", errNumber);
		result.put("errMessages", errMessages);
		result.put("data", obj);
		return gson.toJson(result);
	}
}

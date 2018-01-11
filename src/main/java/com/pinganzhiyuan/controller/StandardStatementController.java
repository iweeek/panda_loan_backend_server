package com.pinganzhiyuan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.ClientVersionExample;
import com.pinganzhiyuan.service.ProductViewService;
import com.pinganzhiyuan.service.StandardStatementService;
import com.pinganzhiyuan.timer.NewDailyStatisticTask;

import io.swagger.annotations.ApiOperation;

@RestController
public class StandardStatementController {
	@Autowired
	private StandardStatementService standardStatementService;
	@Autowired
	private NewDailyStatisticTask newDailyStatisticTask;
	@Autowired
	private ProductViewService productViewService;
	@Autowired
	private ClientVersionMapper clientVersionMapper;
	
	@ApiOperation(value = "标准化报表统计", notes = "标准化报表统计")
    @RequestMapping(value = { "/standardStatement" }, method = RequestMethod.POST)
    public ResponseEntity<?> create() {
		//取所有日志信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String [] dates = new String[]{
				"2017-12-26",
				"2017-12-27","2017-12-28","2017-12-29",
				"2017-12-30","2017-12-31","2018-01-01",
				"2018-01-02","2018-01-03","2018-01-04",
				"2018-01-05","2018-01-06","2018-01-07",
				"2018-01-08","2018-01-09"};
		Date date = new Date();
		for (int i = 0;i<dates.length;i++){
			try {
				date = sdf.parse(dates[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(sdf.format(date));
			String appProductName = "熊猫贷款"; 
			String packageName="com.mg.pandaloan";
			Long channelId = 13L;
			int version = 11;
			standardStatementService.createStandardStatement(date,appProductName,packageName,channelId,version);        
		}
		return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	@ApiOperation(value = "加上渠道的标准化报表统计", notes = "加上渠道的标准化报表统计")
    @RequestMapping(value = { "/newStandardStatement" }, method = RequestMethod.POST)
    public ResponseEntity<?> newCreate() {
		//取所有日志信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String [] dates = new String[]{
				"2017-12-26",
				"2017-12-27","2017-12-28","2017-12-29",
				"2017-12-30","2017-12-31","2018-01-01",
				"2018-01-02","2018-01-03","2018-01-04",
				"2018-01-05","2018-01-06","2018-01-07",
				"2018-01-08","2018-01-09"};
		Date date = new Date();
		for (int i = 0;i<dates.length;i++){
			try {
				date = sdf.parse(dates[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String appProductName = "熊猫贷款"; 
			
			ClientVersionExample example = new ClientVersionExample();
			example.createCriteria().andNameEqualTo(appProductName);
			List<ClientVersion> clientVersions = clientVersionMapper.selectByExample(example);
			for (ClientVersion clientVersion : clientVersions) {
				standardStatementService.createNewStandardStatement(
						date,appProductName,clientVersion.getChannelId(),clientVersion.getVersionCode());
			}
		}
		return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	@ApiOperation(value = "定时任务测试", notes = "定时任务测试")
    @RequestMapping(value = { "/start" }, method = RequestMethod.POST)
    public ResponseEntity<?> job() {
		newDailyStatisticTask.statisticDevice();
        return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
	
	@ApiOperation(value = "产品视图", notes = "产品视图")
    @RequestMapping(value = { "/productView" }, method = RequestMethod.POST)
    public ResponseEntity<?> job1() {
		//取所有日志信息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String [] dates = new String[]{
				"2017-12-26",
				"2017-12-27","2017-12-28","2017-12-29",
				"2017-12-30","2017-12-31","2018-01-01",
				"2018-01-02","2018-01-03","2018-01-04",
				"2018-01-05","2018-01-06","2018-01-07",
				"2018-01-08","2018-01-09"};
		Date date = new Date();
		for (int i=0;i<dates.length;i++){
			try {
				date = sdf.parse(dates[i]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			productViewService.createDataByDate(date);
		}
		return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(null); 
    }
}

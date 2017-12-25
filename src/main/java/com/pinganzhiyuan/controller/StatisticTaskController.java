package com.pinganzhiyuan.controller;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinganzhiyuan.mapper.StatisticMapper;
import com.pinganzhiyuan.model.Statistic;
import com.pinganzhiyuan.service.StatisticTaskService;

import io.swagger.annotations.Api;

@Api(tags = "Task相关接口")
@RestController
@RequestMapping(value="/statisticTasks")
public class StatisticTaskController {
    
    @Autowired
    StatisticTaskService statisticTaskService;
    
    @Autowired
    StatisticMapper statisticMapper;
    
    private static final Logger logger = LogManager.getLogger(StatisticTaskService.class);

//    @RequestMapping(value="/newDevice", method = RequestMethod.POST)
//    public ResponseEntity<?> newDevice(@ApiParam("活动开始时间") @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd")  Date startDate,
//            @ApiParam("活动结束时间") @RequestParam(required = false)  @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
    @RequestMapping(value="/newDevice", method = RequestMethod.POST)
    public ResponseEntity<?> newDevice(Date startDate, Date endDate) {
        try {
            statisticTaskService.statisticTask(startDate, endDate);
            statisticTaskService.productStatisticTask(startDate, endDate);
            
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}


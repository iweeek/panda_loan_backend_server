package com.pinganzhiyuan.timer;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pinganzhiyuan.service.StatisticTaskService;


@Component
public class DailyStatisticTask {
    @Autowired
    private StatisticTaskService statisticTaskService;

    @Scheduled(cron = "0 42 23 * * ?")
    public void job() {
        DateTime yesterday = new DateTime().withMillisOfDay(0).minusDays(1);
        Date startDate = yesterday.toDate();
       
        DateTime now = new DateTime().withMillisOfDay(0);
        Date endDate = now.toDate();
        
        statisticTaskService.statisticTask(startDate, endDate);
        statisticTaskService.productStatisticTask(startDate, endDate);
    }
}

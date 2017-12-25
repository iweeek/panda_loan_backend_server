package com.pinganzhiyuan.service;

import java.util.Date;

public interface StatisticTaskService {
    
    // 新增设备数
	public int newDeviceCount(Date startDate, Date endDate);
	
	// 访问设备数
	public int deviceVisitCount(Date startDate, Date endDate);
	
	// 访问用户数
	public int userVisitCount(Date startDate, Date endDate);
	
	// 新增用户数
    public int newUserCount(Date startDate, Date endDate);
    
    // 产品访问总次数
    public int productVisitTotalCount(Date startDate, Date endDate);
    
    // 产品访问总人数
    public int productVisitUserTotalCount(Date startDate, Date endDate);
	
    // 平均每人浏览产品数 
    public double averageProductVisitCount(Date startDate, Date endDate);

    // 每个产品的访问次数
    public int visitCountByProduct(Date startDate, Date endDate);
    
    // 每个产品的访问人数
    public int visitUserCountByProduct(Date startDate, Date endDate);

    // 每个产品的平均停留时长
    public int averageStayTime(Date startDate, Date endDate);

    void productStatisticTask(Date startDate, Date endDate);

    void statisticTask(Date startDate, Date endDate);
    
}

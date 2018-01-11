package com.pinganzhiyuan.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinganzhiyuan.mapper.ClientVersionMapper;
import com.pinganzhiyuan.mapper.DeviceLogMapper;
import com.pinganzhiyuan.mapper.ProductMapper;
import com.pinganzhiyuan.mapper.ProductViewMapper;
import com.pinganzhiyuan.model.ClientVersion;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductView;
import com.pinganzhiyuan.service.ProductViewService;
@Service
public class ProductViewServiceImpl implements ProductViewService {

	@Autowired
	private ClientVersionMapper clientVersionMapper;
	
	@Autowired
	private ProductViewMapper productViewMapper;
	
	@Autowired
	private DeviceLogMapper deviceLogMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	@Override
	public void createDataByDate(Date date) {
		// TODO Auto-generated method stub
		List<ClientVersion> clientVersions = clientVersionMapper.selectByExample(null);
		List<Product> products = productMapper.selectByExample(null);
		for (ClientVersion clientVersion : clientVersions) {
			for (Product product : products) {
				int uvNumber = deviceLogMapper.selectUvCountByDateAndChannelIdAndVersionAndPid(
						date,clientVersion.getChannelId(),clientVersion.getVersionCode(),product.getId());
				int pvNumber = deviceLogMapper.selectPvCountByDateAndChannelIdAndVersionAndPid(
						date,clientVersion.getChannelId(),clientVersion.getVersionCode(),product.getId());
				System.out.println(uvNumber);
				System.out.println(pvNumber);
				if (uvNumber > 0 || pvNumber > 0) {
					ProductView productView = new ProductView();
					productView.setAppProductName(clientVersion.getName());
					productView.setStatisticDate(date);
					productView.setProductName(product.getTitle());
					productView.setProductPvCount(pvNumber);
					productView.setProductUvCount(uvNumber);
					productViewMapper.insert(productView);
				}
			}
			
		}
	}
	
}

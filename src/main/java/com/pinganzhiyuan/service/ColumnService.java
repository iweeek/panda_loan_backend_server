package com.pinganzhiyuan.service;

import java.util.List;

import com.pinganzhiyuan.model.Column;
import com.pinganzhiyuan.model.Product;
import com.pinganzhiyuan.model.ProductColumnMapping;
import com.pinganzhiyuan.util.ResponseBody;

public interface ColumnService {
    
    int index(List<Column> pointList, ResponseBody resBody);
    
}

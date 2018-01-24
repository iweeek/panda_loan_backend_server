package com.pinganzhiyuan.dto;

public class PageDTO<T> {

    public int pageNumber;
    public int pageSize;
    public int pagesCount;
    public int dataCount;
    public T data;
    public int getPageNumber() {
        return pageNumber;
    }
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getPagesCount() {
        return pagesCount;
    }
    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }
    public int getDataCount() {
        return dataCount;
    }
    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "PageEntity [pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", pagesCount=" + pagesCount
                + ", dataCount=" + dataCount + ", data=" + data + "]";
    }
    
}

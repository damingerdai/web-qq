package org.aming.web.qq.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * @author aming
 * @version 2017-10-09
 */
public class Page implements Serializable{

    private static final int DEFAULT_PAGE_SIZE = 10;

    private int currentPage;
    private int totalPage;
    private int pageSize;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Page(){
        super();
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Page(int currentPage){
        super();
        this.currentPage = currentPage;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Page(int currentPage,int pageSize){
       this(currentPage);
       this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

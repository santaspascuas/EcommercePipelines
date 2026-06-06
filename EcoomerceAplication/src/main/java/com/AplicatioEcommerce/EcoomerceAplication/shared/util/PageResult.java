package com.AplicatioEcommerce.EcoomerceAplication.shared.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageResult<T> {

    private List<T> list;
    private long total;
    private int pageNo;
    private int pageSize;
    private int totalPages;

    public PageResult(Page<T> page) {
        this.list = page.getContent();
        this.total = page.getTotalElements();
        this.pageNo = page.getNumber() + 1;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
    }

    public PageResult(List<T> list, long total, int pageNo, int pageSize) {
        this.list = list;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalPages = pageSize > 0 ? (int) Math.ceil((double) total / pageSize) : 1;
    }

    public List<T> getList() { return list; }
    public long getTotal() { return total; }
    public int getPageNo() { return pageNo; }
    public int getPageSize() { return pageSize; }
    public int getTotalPages() { return totalPages; }
}

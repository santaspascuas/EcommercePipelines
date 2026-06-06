package com.AplicatioEcommerce.EcoomerceAplication.shared.util;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageParam {

    public static final int PAGE_SIZE_NONE = -1;

    @NotNull
    @Min(1)
    private Integer pageNo = 1;

    @NotNull
    @Min(1)
    @Max(200)
    private Integer pageSize = 10;

    public Integer getPageNo() { return pageNo; }
    public void setPageNo(Integer pageNo) { this.pageNo = pageNo; }

    public Integer getPageSize() { return pageSize; }
    public void setPageSize(Integer pageSize) { this.pageSize = pageSize; }

    public Pageable toPageable() {
        return PageRequest.of(pageNo - 1, pageSize);
    }

    public Pageable toPageable(Sort sort) {
        return PageRequest.of(pageNo - 1, pageSize, sort);
    }
}

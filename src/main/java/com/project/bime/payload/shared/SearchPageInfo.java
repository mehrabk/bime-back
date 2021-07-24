package com.project.bime.payload.shared;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Data
public class SearchPageInfo {
    private String query;
    private String orderBy;
    private String order;
    @Min(0)
    private int page;
    @Min(1)
    private int size;
    private int type;
    private int status;
    private int[] actions;
    public Pageable getPageable() {
        if (orderBy != null && order != null && orderBy.length() > 0 && order.length() > 0) {
            Sort sort = Sort.by(orderBy);
            sort = order.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
            return PageRequest.of(page, size, sort);
        }
        return PageRequest.of(page, size);
    }
}

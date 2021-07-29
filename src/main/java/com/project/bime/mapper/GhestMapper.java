package com.project.bime.mapper;

import com.project.bime.model.Ghest;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.ghest.GhestRequest;
import com.project.bime.payload.ghest.GhestResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class GhestMapper {
    public PagedResponse<GhestResponse> toPagedResponse(Page<Ghest> items) {
        if (items.getNumberOfElements() == 0) {
            return new PagedResponse<GhestResponse>(Collections.emptyList(), items.getNumber(), items.getSize(), items.getTotalElements(), items.getTotalPages(), items.isLast());
        }
        return new PagedResponse<GhestResponse>(items.getContent().stream().map(gh -> new GhestResponse(gh)).collect(Collectors.toList()),
                items.getNumber(), items.getSize(), items.getTotalElements(), items.getTotalPages(), items.isLast());
    }

    public Ghest requestToGhest(GhestRequest request, Ghest newGhest) {
        newGhest.setGhestNumber(request.getGhestNumber());
        newGhest.setGhestDate(request.getGhestDate());
        newGhest.setGhestPrice(request.getGhestPrice());
        newGhest.setImageUrl(request.getImageUrl());
//        newGhest.setSmsStatus(request.getSmsStatus());
        newGhest.setNote(request.getNote());
        return newGhest;
    }
}

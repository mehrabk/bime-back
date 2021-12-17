package com.project.bime.mapper;

import com.project.bime.model.Bime;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class BimeMapper {

    public PagedResponse<BimeResponse> toPagedResponse(Page<Bime> items) {
        if (items.getNumberOfElements() == 0) {
            return new PagedResponse<BimeResponse>(Collections.emptyList(), items.getNumber(), items.getSize(), items.getTotalElements(), items.getTotalPages(), items.isLast());
        }
        return new PagedResponse<BimeResponse>(items.getContent().stream().map(b -> new BimeResponse(b)).collect(Collectors.toList()),
                items.getNumber(), items.getSize(), items.getTotalElements(), items.getTotalPages(), items.isLast());
    }

    public Bime requestToBime(BimeRequest request, Bime newBime) {
        newBime.setBimeNumber(request.getBimeNumber());
        newBime.setYektaCode(request.getYektaCode());
        newBime.setTotalPrice(request.getTotalPrice());
        newBime.setPishPardakht(request.getPishPardakht());
        newBime.setContractDate(request.getContractDate());
        newBime.setNote(request.getNote());
        newBime.setType(request.getType());
        return newBime;
    }
}

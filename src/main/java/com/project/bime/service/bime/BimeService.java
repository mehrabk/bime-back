package com.project.bime.service.bime;

import com.project.bime.model.Bime;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import org.springframework.data.domain.Pageable;


public interface BimeService {
    PagedResponse<BimeResponse> findAllByCustomer_Id(long customerId, Pageable pageable);
    Bime save(BimeRequest request, long cId);
}
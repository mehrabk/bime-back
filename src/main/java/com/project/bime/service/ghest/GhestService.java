package com.project.bime.service.ghest;

import com.project.bime.model.Ghest;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.ghest.GhestRequest;
import com.project.bime.payload.ghest.GhestResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import org.springframework.data.domain.Pageable;

public interface GhestService {
    PagedResponse<GhestResponse> findAllByBime_Id(Pageable pageable, long bimeId);

    Ghest save(GhestRequest request, long bimeId);
}

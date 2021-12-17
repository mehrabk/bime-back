package com.project.bime.service.ghest;

import com.project.bime.exception.NotFoundException;
import com.project.bime.mapper.GhestMapper;
import com.project.bime.model.Bime;
import com.project.bime.model.Ghest;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.ghest.GhestRequest;
import com.project.bime.payload.ghest.GhestResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import com.project.bime.repository.BimeRepository;
import com.project.bime.repository.GhestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class GhestServiceImpl implements GhestService{
    private GhestRepository ghestRepository;
    private BimeRepository bimeRepository;
    private GhestMapper ghestMapper;

    @Autowired
    public GhestServiceImpl(GhestRepository ghestRepository, BimeRepository bimeRepository, GhestMapper ghestMapper) {
        this.ghestRepository = ghestRepository;
        this.bimeRepository = bimeRepository;
        this.ghestMapper = ghestMapper;
    }

    @Override
    public PagedResponse<GhestResponse> findAllByBime_Id(Pageable pageable, long bimeId) {
        Page<Ghest> items = ghestRepository.findAllByBime_Id(pageable, bimeId);
        return ghestMapper.toPagedResponse(items);
    }

    @Override
    public Ghest save(GhestRequest request, long bimeId) {
        Bime existBime = bimeRepository.findById(bimeId).orElseThrow(() -> new NotFoundException("Bime with id=" + bimeId + " not found!"));
        Ghest newGhest = request.getId() > 0 ? ghestRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Ghest with id=" + request.getId() + " not found!")) : new Ghest();
        newGhest.setBime(existBime);
        return ghestRepository.save(ghestMapper.requestToGhest(request, newGhest));
    }
}

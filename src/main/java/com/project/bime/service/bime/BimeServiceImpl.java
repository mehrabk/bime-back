package com.project.bime.service.bime;

import com.project.bime.exception.NotFoundException;
import com.project.bime.mapper.BimeMapper;
import com.project.bime.model.Bime;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import com.project.bime.repository.BimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BimeServiceImpl implements BimeService{

    private BimeService bimeService;
    private BimeRepository bimeRepository;
    private BimeMapper bimeMapper;

    @Autowired
    public BimeServiceImpl(BimeService bimeService, BimeRepository bimeRepository, BimeMapper bimeMapper) {
    this.bimeService = bimeService;
        this.bimeRepository = bimeRepository;
        this.bimeMapper = bimeMapper;
    }

    @Override
    public Bime save(BimeRequest request) {
        Optional<Bime> item =  request.getId() > 0 ? bimeRepository.findById(request.getId()): Optional.empty();
        Bime newBime = item.orElseGet(() -> new Bime());
        return bimeRepository.save(bimeMapper.requestToBime(request, newBime));
    }
}


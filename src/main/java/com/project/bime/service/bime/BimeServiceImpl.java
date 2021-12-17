package com.project.bime.service.bime;

import com.project.bime.exception.NotFoundException;
import com.project.bime.mapper.BimeMapper;
import com.project.bime.model.Bime;
import com.project.bime.model.BimeInfo;
import com.project.bime.model.BimeType;
import com.project.bime.model.Customer;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import com.project.bime.repository.BimeInfoRepository;
import com.project.bime.repository.BimeRepository;
import com.project.bime.repository.CustomerRepository;
import com.project.bime.service.ghest.GhestInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BimeServiceImpl implements BimeService{
    private BimeRepository bimeRepository;
    private BimeMapper bimeMapper;
    private CustomerRepository customerRepository;
    private BimeInfoRepository bimeInfoRepository;
    private GhestInitService ghestInitService;

    @Autowired
    public BimeServiceImpl(BimeRepository bimeRepository,
                           BimeMapper bimeMapper,
                           CustomerRepository customerRepository,
                           BimeInfoRepository bimeInfoRepository,
                           GhestInitService ghestInitService
                           ) {
        this.bimeRepository = bimeRepository;
        this.bimeMapper = bimeMapper;
        this.customerRepository = customerRepository;
        this.bimeInfoRepository = bimeInfoRepository;
        this.ghestInitService = ghestInitService;
    }


    @Override
    public PagedResponse<BimeResponse> findAllByCustomer_Id(long customerId, Pageable pageable) {
        Page<Bime> items = bimeRepository.findAllByCustomer_Id(customerId, pageable);
        return bimeMapper.toPagedResponse(items);
    }

    @Override
    public Bime save(BimeRequest request, long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer with id=" + customerId + " not found!"));
        Bime newBime = request.getId() > 0 ? bimeRepository.findById(request.getId()).orElseThrow(() -> new NotFoundException("Bime with id=" + request.getId() + " not found!")) : new Bime();
        if (request.getId() == 0) {
            newBime.setCustomer(customer);
            Bime savedNewBime = bimeRepository.save(bimeMapper.requestToBime(request, newBime));
            if (request.getType() == BimeType.BIME_BADANE) {
                // first bime must initialize (assign id) then bimeinfo can set and connect with bime
                BimeInfo bi = bimeInfoRepository.save(new BimeInfo(request.getCarName(), savedNewBime));
                savedNewBime.setBimeInfo(bi);
            }
            return ghestInitService.ghestInit(savedNewBime, request.getGhestCount() > 0 ? request.getGhestCount() : 1 );
        }
        else {
            if (request.getType() == BimeType.BIME_BADANE) {
                newBime.getBimeInfo().setCarName(request.getCarName());
            }
            Bime updatedBime = bimeRepository.save(bimeMapper.requestToBime(request, newBime));
            return updatedBime;
        }
    }
}


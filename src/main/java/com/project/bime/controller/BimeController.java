package com.project.bime.controller;

import com.project.bime.exception.NotFoundException;
import com.project.bime.model.Bime;
import com.project.bime.payload.ApiResponse;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import com.project.bime.repository.BimeRepository;
import com.project.bime.service.bime.BimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/bime")
public class BimeController {

    private BimeService bimeService;
    private BimeRepository bimeRepository;

    @Autowired
    public BimeController(BimeService bimeService, BimeRepository bimeRepository) {
        this.bimeService = bimeService;
        this.bimeRepository = bimeRepository;
    }

    @GetMapping("/{cId}/list")
    public PagedResponse<BimeResponse> findAllByCustomer_Id(@PathVariable("cId") long customerId, Pageable pageable ) {
        return bimeService.findAllByCustomer_Id(customerId, pageable);
    }


    @PostMapping("/{cId}/save")
    public ResponseEntity<BimeResponse> save(@Valid @RequestBody BimeRequest request, @PathVariable("cId") long customerId ) {
        return ResponseEntity.ok(new BimeResponse(bimeService.save(request, customerId)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id) {
        Bime bime = bimeRepository.findById(id).orElseThrow(() -> new NotFoundException("Bime with id=" + id + " not found!"));
        try {
            bimeRepository.delete(bime);
            return ResponseEntity.ok(new ApiResponse(true, "Bime with id=" + id + " deleted!"));
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, "Error: " + ex.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/info")
    public ResponseEntity<BimeResponse> info(@RequestParam("id") long id) {
        Bime bime = bimeRepository.findById(id).orElseThrow(() -> new NotFoundException("Bime with id=" + id + " not found!"));
        return new ResponseEntity<>(new BimeResponse(bime), HttpStatus.OK);
    }


}

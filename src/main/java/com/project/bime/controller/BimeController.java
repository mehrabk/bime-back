package com.project.bime.controller;

import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.bime.BimeRequest;
import com.project.bime.payload.bime.BimeResponse;
import com.project.bime.repository.BimeRepository;
import com.project.bime.service.bime.BimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{cId}/save")
    public ResponseEntity<BimeResponse> save(@RequestBody BimeRequest request, @RequestParam("cId") long id ) {
        return ResponseEntity.ok(new BimeResponse(bimeService.save(request)));
    }

}

package com.project.bime.controller;

import com.project.bime.exception.NotFoundException;
import com.project.bime.model.Bime;
import com.project.bime.model.Ghest;
import com.project.bime.payload.ApiResponse;
import com.project.bime.payload.PagedResponse;
import com.project.bime.payload.ghest.GhestRequest;
import com.project.bime.payload.ghest.GhestResponse;
import com.project.bime.payload.shared.SearchPageInfo;
import com.project.bime.repository.GhestRepository;
import com.project.bime.service.ghest.GhestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ghest")
public class GhestController {

    private GhestRepository ghestRepository;
    private GhestService ghestService;

    @Autowired
    public GhestController(GhestRepository ghestRepository, GhestService ghestService) {
        this.ghestRepository = ghestRepository;
        this.ghestService = ghestService;
    }

    @GetMapping("/{bId}/list")
    public PagedResponse<GhestResponse> findAllByBime_Id(Pageable pageable, @PathVariable("bId") long bimeId) {
        return ghestService.findAllByBime_Id(pageable, bimeId);
    }

    @PostMapping("/{bId}/save")
    public ResponseEntity<GhestResponse> save(@Valid @RequestBody GhestRequest request, @PathVariable("bId") long bimeId) {
        return ResponseEntity.ok(new GhestResponse(ghestService.save(request, bimeId)));
    }


    @GetMapping("/info")
    public ResponseEntity<GhestResponse> info(@RequestParam("id") long id) {
       Ghest ghest = ghestRepository.findById(id).orElseThrow(() -> new NotFoundException("Bime with id=" + id + " not found!"));
       return new ResponseEntity<>(new GhestResponse(ghest), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") long id) {
        Ghest ghest = ghestRepository.findById(id).orElseThrow(() -> new NotFoundException("Ghest with id=" + id + " not found!"));
        try {
            ghestRepository.delete(ghest);
            return new ResponseEntity<>(new ApiResponse(true, "Ghest with id=" + id + " deleted!"), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ApiResponse(false, "Error: " + ex.getMessage()), HttpStatus.BAD_REQUEST);

        }
    }
}

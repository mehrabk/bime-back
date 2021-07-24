package com.project.bime.service.bime;

import com.project.bime.model.Bime;
import com.project.bime.payload.bime.BimeRequest;

import java.util.List;
import java.util.Optional;

public interface BimeService {
    Bime save(BimeRequest request);
}
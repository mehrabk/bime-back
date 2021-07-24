package com.project.bime.mapper;

import com.project.bime.model.Bime;
import com.project.bime.model.BimeType;
import com.project.bime.payload.bime.BimeRequest;
import org.springframework.stereotype.Component;

@Component
public class BimeMapper {

    public Bime requestToBime(BimeRequest request, Bime newBime) {
        newBime.setBimeNumber(request.getBimeNumber());
        newBime.setYektaCode(request.getYektaCode());
        newBime.setTotalPrice(request.getTotalPrice());
        newBime.setPishPardakht(request.getPishpardakht());
        newBime.setContractDate(request.getContractData());
        newBime.setNote(request.getNote());
        newBime.setType(BimeType.getType(request.getType()));
        if (request.getType() == BimeType.BIME_BADANE.getValue()) {
            newBime.getBimeInfo().setCarName(request.getCarName());
        }
        if (request.getType() == BimeType.BIME_SALES.getValue()) {

        }
        return newBime;
    }
}

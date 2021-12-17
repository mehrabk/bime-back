package com.project.bime.payload.ghest;

import com.project.bime.model.Ghest;
import lombok.Data;

import javax.persistence.Lob;
import java.util.Date;

@Data
public class GhestResponse {
    private Long id;
    private int ghestNumber;
    private Date ghestDate;
    private long ghestPrice;
    private Date createdAt;
    private Date updatedAt;
    @Lob
    private String imageUrl;
    private String smsStatus;
    @Lob
    private String note;

    public GhestResponse(Ghest ghest) {
        setId(ghest.getId());
        setGhestNumber(ghest.getGhestNumber());
        setGhestPrice(ghest.getGhestPrice());
        setCreatedAt(ghest.getCreatedAt());
        setUpdatedAt(ghest.getUpdatedAt());
        setGhestDate(ghest.getGhestDate());
        setImageUrl(ghest.getImageUrl());
        setSmsStatus(ghest.getSmsStatus());
        setNote(ghest.getNote());
    }
}

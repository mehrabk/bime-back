package com.project.bime.payload.bime;
import com.project.bime.model.Bime;
import com.project.bime.model.BimeType;
import lombok.Data;
import javax.persistence.Lob;

import java.util.Date;

@Data
public class BimeResponse {
    private Long id;
    private String bimeNumber;
    private long yektaCode;
    private long totalPrice;
    private long pishPardakht;
    private Date contractDate;
    private Date createdAt;
    private Date updatedAt;
    @Lob
    private String note;
    private String type;

    // bime badane
    private String carName;

    // extra
    private int ghestCount;

    public BimeResponse(Bime bime) {
        setId(bime.getId());
        setBimeNumber(bime.getBimeNumber());
        setYektaCode(bime.getYektaCode());
        setTotalPrice(bime.getTotalPrice());
        setPishPardakht(bime.getPishPardakht());
        setContractDate(bime.getContractDate());
        setCreatedAt(bime.getCreatedAt());
        setUpdatedAt(bime.getUpdatedAt());
        setNote(bime.getNote());
        setType(bime.getType().getLabel());
        if (bime.getType() == BimeType.BIME_BADANE) {
            setCarName(bime.getBimeInfo().getCarName());
        }
        setGhestCount(bime.getGhestList() != null ? bime.getGhestList().size() > 0 ? bime.getGhestList().size() : 0 : 0);
    }
}

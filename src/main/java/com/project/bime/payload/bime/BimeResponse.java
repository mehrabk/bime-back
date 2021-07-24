package com.project.bime.payload.bime;
import com.project.bime.model.Bime;
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
    @Lob
    private String note;
    private String type;

    public BimeResponse(Bime bime) {
        setId(bime.getId());
        setBimeNumber(bime.getBimeNumber());
        setYektaCode(bime.getYektaCode());
        setTotalPrice(bime.getTotalPrice());
        setPishPardakht(bime.getPishPardakht());
        setContractDate(bime.getContractDate());
        setNote(bime.getNote());
    }
}

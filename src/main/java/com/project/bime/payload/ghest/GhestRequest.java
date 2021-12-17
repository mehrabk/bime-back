package com.project.bime.payload.ghest;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
public class GhestRequest {

    @NotNull
    @Min(0)
    private Long id;

    @NotNull @Positive(message = "Must be Positive Number")
    private Integer ghestNumber;

    @NotNull @DateTimeFormat
    private Date ghestDate;

    @NotNull @Positive(message = "Must be Positive Number")
    private Long ghestPrice;

    @Lob
    private String imageUrl;

    private String smsStatus;

    @Lob
    private String note;
}

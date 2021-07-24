package com.project.bime.payload.bime;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class BimeRequest {
    private Long id;
    @NotNull @NotBlank
    private String bimeNumber;
    @NotNull @NotBlank
    private long yektaCode;
    @NotNull @NotBlank
    private long totalPrice;
    @NotNull @NotBlank
    private long pishpardakht;
    @NotNull @NotBlank
    private Date contractData;
    private String note;
    @NotNull @NotBlank @Min(1) @Max(2)
    private int type;

    // bimeBadane
    private String carName;
}

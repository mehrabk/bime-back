package com.project.bime.payload.bime;

import com.project.bime.model.BimeType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class BimeRequest {

    // private long id with @NotNull => if request bime object without id variable(null) get to server this id assign to zero(0)(long -> always is 0)
    // private Long id with @NotNull => it can not be null and request object without id variable throw an NotNull validation error
    @NotNull
    @Min(0)
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String bimeNumber;

    @NotNull
    @Positive(message = "Must be Positive Number")
    private Long yektaCode;

    @NotNull
    @Positive(message = "Must be Positive Number")
    private Long totalPrice;

    @NotNull
    @Positive(message = "Must be Positive Number")
    private Long pishPardakht;

    @NotNull
    @DateTimeFormat
    private Date contractDate;

    private String note;

    @NotNull
    @Positive(message = "Must be Positive Number")
    private int type;

    // bimeBadane
    private String carName;

    // extra
    @NotNull
    @Min(0)
    private Integer ghestCount;

    public BimeType getType() {
        return BimeType.getType(type);
    }

    public void setType(BimeType bimeType) {
        this.type = bimeType.getValue();
    }
}

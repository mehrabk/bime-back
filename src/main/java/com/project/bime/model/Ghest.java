package com.project.bime.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ghest extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(0)
    private int ghestNumber;

    @NotNull
    private Date ghestDate;

    @NotNull
    private long ghestPrice;

    @Lob
    private String imageUrl;


    private String smsStatus;

    @Lob
    private String note;

    @ManyToOne
    @JoinColumn(name = "bime_id", referencedColumnName = "id")
    @JsonIgnore
    private Bime bime;
}

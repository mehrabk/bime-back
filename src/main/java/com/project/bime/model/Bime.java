package com.project.bime.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bime extends AuditModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull @NotBlank @Size(min = 3)
    private String bimeNumber;

    @NotNull @Positive
    private long yektaCode;

    @NotNull @Positive
    private long totalPrice;

    @NotNull @Positive
    private long pishPardakht;

    @NotNull
    private Date contractDate;

    @Lob
    private String note;

    @NotNull @Positive
    private int type;

    // the @PrimaryKeyJoinColumn annotation, which indicates that the primary key of the bime entity is used as the foreign key value for the associated bimeInfo entity.
    @OneToOne(mappedBy = "bime", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private BimeInfo bimeInfo;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "bime", cascade = CascadeType.ALL)
    private List<Ghest> ghestList;

    public Bime() {}

    public BimeType getType() {
        return BimeType.getType(type);
    }

    public void setType(BimeType bimeType) {
        this.type = bimeType.getValue();
    }
}

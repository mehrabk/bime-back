package com.project.bime.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class BimeInfo {

    @Id
    @Column(name = "bime_id")
    private Long id;

    // the @MapsId annotation, which indicates that the primary key values will be copied from the bime entity.
    @OneToOne
    @MapsId
    @JoinColumn(name = "bime_id")
    private Bime bime;

    private String carName;

    public BimeInfo(String carName, Bime bime){
        this.carName = carName;
        this.bime = bime;
    }


}

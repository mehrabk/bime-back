package com.project.bime.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName name;

    public Role() {}
    public Role(RoleName name) {
        this.name = name;
    }
}

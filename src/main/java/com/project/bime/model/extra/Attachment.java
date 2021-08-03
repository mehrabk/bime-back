package com.project.bime.model.extra;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "attachments", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "source"
        })
})
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;

    public Attachment(String source) {
        this.source = source;
    }

}

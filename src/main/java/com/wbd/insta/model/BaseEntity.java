package com.wbd.insta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Data
@Getter
@Setter
//Class generates id for each of the model classes
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Common_seq")
    @SequenceGenerator(name = "common_seq", sequenceName = "common_sequence", allocationSize = 1)
    private long id;
}

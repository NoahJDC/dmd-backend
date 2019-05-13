package com.dm_daddy.first_edition.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Feats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column
    private String name;

    @Lob
    private String description;

    @Column
    private String noteOne;

    @Column
    private String noteTwo;

    @Column
    private String noteThree;

    @Column
    private String noteFour;

    @Column
    private String noteFive;

    @Column
    private String prerequisite;





}

package com.dm_daddy.first_edition.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="refCode")
@Getter
@Setter
public class RefCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private Long parentId;

    private String name;

    private String description;



}

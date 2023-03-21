package com.example.excelreader.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "Report")
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "imeis")
    private String imeis;

    public Template(String imeis) {
        this.imeis = imeis;
    }
}

package com.kelompok6.hateoas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "mahasiswa")
@NoArgsConstructor
public class Mahasiswa extends RepresentationModel<Mahasiswa> {

    @Id
    private String nim;

    @Column(name = "nama")
    private String nama;

    @Column(name = "prodi")
    private String prodi;

}
package com.kelompok6.hateoas.repository;

import com.kelompok6.hateoas.entity.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, String> {
}

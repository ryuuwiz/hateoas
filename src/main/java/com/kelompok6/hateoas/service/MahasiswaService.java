package com.kelompok6.hateoas.service;

import com.kelompok6.hateoas.entity.Mahasiswa;
import com.kelompok6.hateoas.repository.MahasiswaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository repository;

    public List<Mahasiswa> getAll() {
        return repository.findAll();
    }

    public Mahasiswa addOne(Mahasiswa mhs) {
        return repository.save(mhs);
    }

    public Optional<Mahasiswa> findByNim(String nim) {
        return repository.findById(nim);
    }
}

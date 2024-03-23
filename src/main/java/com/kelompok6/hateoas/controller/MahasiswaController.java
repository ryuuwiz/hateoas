package com.kelompok6.hateoas.controller;

import com.kelompok6.hateoas.entity.Mahasiswa;
import com.kelompok6.hateoas.payload.MahasiswaRequest;
import com.kelompok6.hateoas.repository.MahasiswaRepository;
import com.kelompok6.hateoas.service.MahasiswaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class MahasiswaController {
    @Autowired
    private MahasiswaRepository mhsRepository;

    @Autowired
    private MahasiswaService mhsService;

    @GetMapping("/mhs")
    public ResponseEntity<CollectionModel<Mahasiswa>> getAllMahasiswa() {
        try {
            List<Mahasiswa> mhs = mhsService.getAll();

            for(Mahasiswa m : mhs) m.add(linkTo(methodOn(MahasiswaController.class).getMahasiswaByNim(m.getNim())).withSelfRel());

            CollectionModel<Mahasiswa> model = CollectionModel.of(mhs);
            model.add(linkTo(methodOn(MahasiswaController.class).getAllMahasiswa()).withSelfRel());

            return new ResponseEntity<>(model, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mhs/{nim}")
    public ResponseEntity<Mahasiswa> getMahasiswaByNim(@PathVariable("nim") String nim) {
        Optional<Mahasiswa> mhsData = mhsService.findByNim(nim);

        mhsData.get().add(linkTo(methodOn(MahasiswaController.class).getMahasiswaByNim(mhsData.get().getNim())).withSelfRel());

        return mhsData.map(mahasiswa -> new ResponseEntity<>(mahasiswa, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/mhs")
    public ResponseEntity<Mahasiswa> createMahasiswa(@Valid @RequestBody MahasiswaRequest mhsRequest) {
        try {
            Mahasiswa _mhs = mhsService.addOne(new Mahasiswa(mhsRequest.getNim(), mhsRequest.getNama(), mhsRequest.getProdi()));

            _mhs.add(linkTo(methodOn(MahasiswaController.class).getMahasiswaByNim(_mhs.getNim())).withSelfRel());
            _mhs.add(linkTo(methodOn(MahasiswaController.class).getAllMahasiswa()).withRel(IanaLinkRelations.COLLECTION));

            return new ResponseEntity<>(_mhs, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

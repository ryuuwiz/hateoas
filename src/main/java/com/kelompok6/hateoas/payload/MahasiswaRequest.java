package com.kelompok6.hateoas.payload;

import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

@Getter
public class MahasiswaRequest {
    @NotBlank(message = "The nim is required")
    @Size(min = 12, max = 13, message = "The nim must be 12 to 13 characters ")
    private String nim;
    @NotBlank(message = "The nama is required")
    @Size(min = 3, max = 30, message = "The nama must be 3 to 30 characters ")

    private String nama;
    @NotBlank(message = "The prodi is required")
    @Size(min = 3, max = 30, message = "The prodi must be 3 to 30 characters ")
    private String prodi;
}

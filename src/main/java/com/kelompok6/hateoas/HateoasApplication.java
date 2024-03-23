package com.kelompok6.hateoas;

import com.kelompok6.hateoas.entity.Mahasiswa;
import com.kelompok6.hateoas.repository.MahasiswaRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.logging.Logger;

@SpringBootApplication
public class HateoasApplication {

	@Autowired
	private MahasiswaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(HateoasApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return (args) -> {
			repository.save(new Mahasiswa("211011450106", "Ryu Kurnianto Putra", "Teknik Informatika"));
			repository.save(new Mahasiswa("211011450107", "John Doe", "Teknik Informatika"));
			repository.save(new Mahasiswa("211011450108", "Jessica Doe", "Teknik Informatika"));
		};
	}

}

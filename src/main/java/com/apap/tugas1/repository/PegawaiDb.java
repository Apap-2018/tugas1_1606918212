package com.apap.tugas1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	
	Optional<PegawaiModel> findByNip(String NIP);
	
	Optional<PegawaiModel> findFirstPegawaiByInstansiIdOrderByTanggalLahirAsc(Long idInstansi);
	
	Optional<PegawaiModel> findFirstPegawaiByInstansiIdOrderByTanggalLahirDesc(Long idInstansi);

}

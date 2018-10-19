package com.apap.tugas1.service;

import java.util.Optional;

import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	
	Optional<PegawaiModel> getPegawaiDetailByNip(String NIP);
	
	Optional<PegawaiModel> getPegawaiTermuda(Long idInstansi);
	
	Optional<PegawaiModel> getPegawaiTertua(Long idInstansi);
	
	
	
}

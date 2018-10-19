package com.apap.tugas1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public Optional<PegawaiModel> getPegawaiDetailByNip(String NIP) {
		return pegawaiDb.findByNip(NIP);
	}
	@Override
	public Optional<PegawaiModel> getPegawaiTermuda(Long idInstansi){
		return pegawaiDb.findFirstPegawaiByInstansiIdOrderByTanggalLahirAsc(idInstansi);
	}
	
	public Optional<PegawaiModel> getPegawaiTertua(Long idInstansi){
		return pegawaiDb.findFirstPegawaiByInstansiIdOrderByTanggalLahirDesc(idInstansi);
	}
	

}

package com.apap.tugas1.service;

import java.util.Date;
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
	
	@Override
	public PegawaiModel save(PegawaiModel pegawai) {
		System.out.println(pegawai.getId());
		pegawai.setNip(generateNip(pegawai));
		
		pegawaiDb.save(pegawai);
		return pegawai;
		
	}
	
	private String generateNip(PegawaiModel pegawai) {
		Long idInstansi = pegawai.getInstansi().getId();
		Date birthdate = pegawai.getTanggalLahir();

        int tanggal = birthdate.getDate();
        int bulan = birthdate.getMonth() + 1;
        int tahun = birthdate.getYear() % 100;

        String ulangTahunString = (tanggal >= 10? Integer.toString(tanggal): "0" + tanggal);
        ulangTahunString += (bulan >= 10 ? bulan : "0" + bulan);
        ulangTahunString += tahun;

        String prefixNip = idInstansi + ulangTahunString + pegawai.getTahun_masuk();
        Optional<PegawaiModel> prefixSama = pegawaiDb.findFirstByNipStartingWithOrderByNipDesc(prefixNip);

        int sequence = 1;
        if (prefixSama.isPresent()) {
            sequence += Integer.parseInt(prefixSama.get().getNip().substring(14));
        }

        return prefixNip + (sequence > 10 ? sequence : "0" + sequence);
    
	}
	

}

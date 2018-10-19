package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	
	boolean addJabatan(JabatanModel jabatan);
	
	List<JabatanModel> findAll();
	
	JabatanModel getJabatanById(Long id);
	
	JabatanModel save(JabatanModel jabatan);
	
	void deleteById(Long id);

}

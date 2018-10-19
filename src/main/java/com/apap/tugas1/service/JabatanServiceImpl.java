package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
@Transactional
public class JabatanServiceImpl implements JabatanService{
	
	@Autowired
	private JabatanDb jabatanDb;
	
	@Override
	public boolean addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
		return true;
	}
	
	@Override
	public List<JabatanModel> findAll(){
		return jabatanDb.findAll();
	}
	
	@Override
	public JabatanModel getJabatanById(Long id){
		return jabatanDb.getOne(id);
		
	}
	
	@Override
	public JabatanModel save(JabatanModel jabatan) {
		return jabatanDb.save(jabatan);
	}

	
	@Override
	public void deleteById(Long id) {
		jabatanDb.deleteById(id);
	}
}

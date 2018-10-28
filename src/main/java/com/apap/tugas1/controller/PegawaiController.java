package com.apap.tugas1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	
	@Autowired
	private PegawaiService pegawaiService; 
	
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> jabatan = jabatanService.findAll();
		List<InstansiModel> instansi = instansiService.findAll();
		
		model.addAttribute("listJabatan", jabatan);
		model.addAttribute("listInstansi", instansi);
		
		return "home";
	}
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String find(@RequestParam String nip, Model model) {
		Optional<PegawaiModel> pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		if(pegawai.isPresent()) {
			PegawaiModel ambil = pegawai.get();
			double gaji = 0;
			for(JabatanModel jabatan : ambil.getJabatan()) {
				if(jabatan.getGaji_pokok()>gaji) {
					gaji = jabatan.getGaji_pokok();
				}
			}
			double persentase = ambil.getInstansi().getProvinsi().getPersentase_tunjangan();
			double gajiTotal = gaji + gaji*persentase;
			model.addAttribute("gajiTotal", gajiTotal);
			model.addAttribute("pegawai", ambil);
			return "dataPegawai";
		}else {
			return "dataPegawaiNotFound";
		}
		
	}
	
	@RequestMapping(value="/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewMudaTua(@RequestParam Long idInstansi, Model model) {
		Optional<PegawaiModel> pegawaiMuda = pegawaiService.getPegawaiTermuda(idInstansi);
		Optional<PegawaiModel> pegawaiTua = pegawaiService.getPegawaiTertua(idInstansi);
		if(pegawaiMuda.isPresent()&&pegawaiTua.isPresent()) {
			model.addAttribute("pegawaiMuda", pegawaiMuda.get());
			model.addAttribute("pegawaiTua", pegawaiTua.get());
			return "pegawaiTermudaTertua";
		}else {
			return "dataPegawaiNotFound";
		}
		
		
	}
	
	@RequestMapping(value="/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		model.addAttribute("listProvinsi", provinsiService.findAll());
		model.addAttribute("listInstansi", instansiService.findAll());
		model.addAttribute("listJabatan", jabatanService.findAll());
		model.addAttribute("pegawai", new PegawaiModel());
		return "addPegawai";
	}
	
	
	

}

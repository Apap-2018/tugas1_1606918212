package com.apap.tugas1.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tugas1.message.Message;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;

@Controller
public class JabatanController {
	
	@Autowired
	JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String addJabatan(Model model) {
		 model.addAttribute("jabatan", new JabatanModel());
		 return "addJabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatan(@ModelAttribute JabatanModel newJabatan, Model model) {
		jabatanService.addJabatan(newJabatan);
		Message message = new Message();
		message.setTitle("Hooray!");
		message.setContent("Jabatan berhasil ditambahkan.");
		message.setType(Message.Type.SUCCESS);
		
		model.addAttribute("jabatan", new JabatanModel());
		model.addAttribute("message", message);
		return "addJabatan";
	}
	
	@RequestMapping(value="/jabatan/view", method = RequestMethod.GET)
	private String viewJabatan(@RequestParam Long idJabatan, Model model) {
		try {
			JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);	
			model.addAttribute("jabatan", jabatan);
			return "detailJabatan";
		} catch (EntityNotFoundException e) {
			return "detailJabatanNotFound";
		}
	}
	
	@RequestMapping(value="/jabatan/viewall", method = RequestMethod.GET)
	private String viewAllJabatan(Model model) {
		List<JabatanModel> listJabatan = jabatanService.findAll();
		model.addAttribute("listJabatan", listJabatan);
		return "listJabatan";
		
	}
	
	
	@RequestMapping(value="/jabatan/ubah", method = RequestMethod.GET)
	private String editJabatan(@RequestParam Long idJabatan, Model model) {
		try {
			JabatanModel jabatan = jabatanService.getJabatanById(idJabatan);	

			model.addAttribute("jabatan", jabatan);
			return "editJabatan";
		} catch (EntityNotFoundException e) {
			
			return "detailJabatanNotFound";
		}
	}
	
	@RequestMapping(value="/jabatan/ubah", method = RequestMethod.POST)
	private String postJabatan(@ModelAttribute JabatanModel jabatanModel, Model model, RedirectAttributes redirect) {
		try {
			jabatanService.save(jabatanModel);
			
			Message message = new Message();
			redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
			
			return "redirect:/jabatan/view?idJabatan=" + jabatanModel.getId();
	
		} catch (EntityNotFoundException e) {
			return "detailJabatanNotFound";
		}
		
	}
	
	@RequestMapping(value="/jabatan/hapus", method=RequestMethod.POST)
	private String deleteJabatan(@ModelAttribute JabatanModel jabatanModel, Model model, RedirectAttributes redirect) {
		try {
			jabatanService.deleteById(jabatanModel.getId());
			
			Message message = new Message();
			message.setTitle("Hooray!");
			message.setContent("Jabatan berhasil dihapus.");
			message.setType(Message.Type.SUCCESS);
			redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
			
			return "redirect:/";
		}catch(Exception e){
			
			Message message = new Message();
			message.setTitle("Oops!");
			message.setContent("Jabatan tidak dapat dihapus.");
			message.setType(Message.Type.WARNING);
			redirect.addFlashAttribute(Message.MESSAGE_NAME, message);
			
			return "redirect:/jabatan/view?idJabatan=" + jabatanModel.getId();
		}
	}
	
	
}

package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="pegawai")
public class PegawaiModel implements Serializable{
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="NIP", nullable=false, unique=true)
	private String nip;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable=false)
	private String nama;
	
	@NotNull
	@Size(max=255)
	@Column(name="tempat_lahir", nullable=false)
	private String tempat_lahir;

	@NotNull
	@Column(name="tanggal_lahir", nullable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date tanggalLahir;
	
	@NotNull
	@Size(max=255)
	@Column(name="tahun_masuk", nullable=false)
	private String tahun_masuk;
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_instansi", nullable=false)
	private InstansiModel instansi;

	@JoinTable(name="jabatan_pegawai", 
			joinColumns = {
					@JoinColumn(name="id_pegawai")
			},
			inverseJoinColumns = {
					@JoinColumn(name="id_jabatan")
			}
	)
	@ManyToMany
	private List<JabatanModel> jabatan;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String NIP) {
		this.nip = NIP;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempat_lahir() {
		return tempat_lahir;
	}

	public void setTempat_lahir(String tempat_lahir) {
		this.tempat_lahir = tempat_lahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggal_lahir) {
		this.tanggalLahir = tanggal_lahir;
	}

	public String getTahun_masuk() {
		return tahun_masuk;
	}

	public void setTahun_masuk(String tahun_masuk) {
		this.tahun_masuk = tahun_masuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}
	
	public List<JabatanModel> getJabatan() {
		return jabatan;
	}

	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatan = jabatan;
	}

}

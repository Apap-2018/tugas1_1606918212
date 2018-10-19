package com.apap.tugas1.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="jabatan_pegawai")
public class JabatanPegawaiModel {

	@Id
	@NotNull
	@Size(max=20)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=20)
	@JoinColumn(name="id_pegawai", referencedColumnName="pegawai.id", nullable=false)
	private long id_pegawai;
	
	@NotNull
	@Size(max=20)
	@JoinColumn(name="id_jabatan", referencedColumnName="jabatan.id", nullable=false)
	private long id_jabatan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId_pegawai() {
		return id_pegawai;
	}

	public void setId_pegawai(long id_pegawai) {
		this.id_pegawai = id_pegawai;
	}

	public long getId_jabatan() {
		return id_jabatan;
	}

	public void setId_jabatan(long id_jabatan) {
		this.id_jabatan = id_jabatan;
	}
	
	
}

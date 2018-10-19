package com.apap.tugas1.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
@Table (name="provinsi")
public class ProvinsiModel {
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Double getPersentase_tunjangan() {
		return persentase_tunjangan;
	}

	public void setPersentase_tunjangan(Double persentase_tunjangan) {
		this.persentase_tunjangan = persentase_tunjangan;
	}

	@Id
	@NotNull
	@Size(max=20)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=255)
	@Column(name="nama", nullable=false)
	private String nama;
	
	@NotNull
	@Column(name="persentase_tunjangan", nullable=false)
	private Double persentase_tunjangan;

}

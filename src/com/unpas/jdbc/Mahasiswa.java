package com.unpas.jdbc;

public class Mahasiswa {
	private int id;
	private String namadepan, namabelakang, email;
	
	public Mahasiswa(int id,String namadepan, String namabelakang, String email) {
		super();
		this.id = id;
		this.namadepan = namadepan;
		this.namabelakang = namabelakang;
		this.email = email;
	}
	

	public Mahasiswa(String namadepan, String namabelakang, String email) {
		super();
		this.namadepan = namadepan;
		this.namabelakang = namabelakang;
		this.email = email;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNamadepan() {
		return namadepan;
	}

	public void setNamadepan(String namadepan) {
		this.namadepan = namadepan;
	}

	public String getNamabelakang() {
		return namabelakang;
	}

	public void setNamabelakang(String namabelakang) {
		this.namabelakang = namabelakang;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Mahasiswa [id=" + id + ", namadepan=" + namadepan + ", namabelakang=" + namabelakang + ", email="
				+ email + "]";
	}
	
	
	
}

package com.example.prayerschedule.models;

public class Kuliah {
    private String nim, nama, kode_matkul, nama_matkul, sks;


    public Kuliah(String nim, String nama, String kode_matkul, String nama_matkul, String sks) {
        this.nim = nim;
        this.nama = nama;
        this.kode_matkul = kode_matkul;
        this.nama_matkul = nama_matkul;
        this.sks = sks;
    }

    public Kuliah() {
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode_matkul() {
        return kode_matkul;
    }

    public void setKode_matkul(String kode_matkul) {
        this.kode_matkul = kode_matkul;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getSks() {
        return sks;
    }

    public void setSks(String sks) {
        this.sks = sks;
    }
}

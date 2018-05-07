package org.d3ifcool.livit.entity;

/**
 * Represent of user's recommendation
 * Consisting title, description, and a state wheter user want to reminded or not
 */
public class Recommendation {
    private String nama;
    private String tipe;

    public Recommendation(String nama, String tipe) {
        this.nama = nama;
        this.tipe = tipe;
    }

    public String getNama() {
        return nama;
    }

    public String getTipe() {
        return tipe;
    }
}

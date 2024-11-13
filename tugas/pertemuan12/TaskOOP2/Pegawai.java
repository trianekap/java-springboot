import java.util.Scanner;

public class Pegawai {
    private String id;
    private String nama;
    private String alamat;
    private String nomorTelepon;
    private String jabatan;

    public Pegawai(String id, String nama, String alamat, String nomorTelepon, String jabatan) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.nomorTelepon = nomorTelepon;
        this.jabatan = jabatan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String toString() {
        return "id= " + id  + ", nama= " + nama + ", alamat= " + alamat + ", nomorTelepon= " + nomorTelepon + ", jabatan= " + jabatan;
    }
}

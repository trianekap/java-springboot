import java.util.ArrayList;

public class Kamar {
    private String nomorKamar;
    private String tipeKamar;
    private int harga;

    public Kamar(String nomorKamar, String tipeKamar, int harga) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.harga = harga;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public void setTipeKamar(String tipeKamar) {
        this.tipeKamar = tipeKamar;
    }

    public int getHargaKamar() {
        return harga;
    }

    public void setHargaKamar(int harga) {
        this.harga = harga;
    }

    public String tampilkanInfoKamar() {
        return nomorKamar + " " + tipeKamar + " " + harga;
    }

}
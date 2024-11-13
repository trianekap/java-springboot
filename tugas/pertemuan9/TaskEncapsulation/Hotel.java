import java.util.*;

public class Hotel {
    private String namaHotel;
    private List<Kamar> kamarList;
    private List<Reservasi> reservasiList;

    public Hotel(String namaHotel) {
        this.namaHotel = namaHotel;
        this.kamarList = new ArrayList<>();
        this.reservasiList = new ArrayList<>();
    }

    public String getNamaHotel() {
        return namaHotel;
    }

    public void setNamaHotel(String namaHotel) {
        this.namaHotel = namaHotel;
    }

    public List<Kamar> getKamarList() {
        return kamarList;
    }

    public void setKamarList(List<Kamar> kamarList) {
        this.kamarList = kamarList;
    }

    public List<Reservasi> getReservasiList() {
        return reservasiList;
    }

    public void setReservasiList(List<Reservasi> reservasiList) {
        this.reservasiList = reservasiList;
    }

    public void tambahKamar(Kamar kamar) {
        kamarList.add(kamar);
    }

    public void tambahReservasi(Reservasi reservasi) {
        reservasiList.add(reservasi);
    }

    public String tampilkanInfoHotel() {
        String info = "Hotel: " + namaHotel + "\n";
        info += "Daftar Kamar:\n";
        for (Kamar k : kamarList) {
            info += k.tampilkanInfoKamar() + "\n";
        }
        info += "Daftar Reservasi:\n";
        for (Reservasi r : reservasiList) {
            info += r.tampilkanInfoReservasi() + "\n";
        }
        return info;
    }


}
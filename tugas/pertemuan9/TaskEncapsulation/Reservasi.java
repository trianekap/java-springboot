import java.util.ArrayList;

public class Reservasi {
    private String tanggalCheckIn;
    private String tanggalCheckOut;
    private ArrayList<Tamu> tamus;
    private ArrayList<Kamar> kamars;

    public Reservasi(String tanggalCheckIn, String tanggalCheckOut) {
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.tamus = new ArrayList<>();
        this.kamars = new ArrayList<>();
    }

    public void tambahTamu(Tamu tamu) {
        tamus.add(tamu);
    }

    public void tambahKamar(Kamar kamar) {
        kamars.add(kamar);
    }

    public String getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public void setTanggalCheckIn(String tanggalCheckIn) {
        this.tanggalCheckIn = tanggalCheckIn;
    }

    public String getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public void setTanggalCheckOut(String tanggalCheckOut) {
        this.tanggalCheckOut = tanggalCheckOut;
    }

    public void tampilkanTamu() {
        for (Tamu t : tamus) {
            System.out.println(t.tampilkanInfoTamu());
        }
    }

    public void tampilkanKamar() {
        for (Kamar k : kamars) {
            System.out.println(k.tampilkanInfoKamar());
        }
    }

    public String tampilkanInfoReservasi() {
    String info = "";
    for (Tamu t : tamus) {
        info += t.tampilkanInfoTamu() + " ";
    }
    for (Kamar k : kamars) {
        info += k.tampilkanInfoKamar() + " ";
    }
    info += "Check-in: " + tanggalCheckIn + "\n";
    info += "Check-out: " + tanggalCheckOut;
    return info;
	}
}
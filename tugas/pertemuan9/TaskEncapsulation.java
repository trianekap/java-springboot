import java.util.ArrayList;
import java.util.List;

public class TaskEncapsulation {
    public static void main(String[] args) {
        Tamu tamu1 = new Tamu("John", "CUS_1", "john@gmail.com");
        Tamu tamu2 = new Tamu("chris", "CUS_2", "chris@gmail.com");
        System.out.println(tamu1.getNama());
        System.out.println(tamu1.getId());
        System.out.println(tamu1.getEmail());
        System.out.println(tamu1.tampilkanInfoTamu());
        System.out.println("=======================");

        Kamar kamar1 = new Kamar("10", "melati", 100000);
        Kamar kamar2 = new Kamar("12", "mawar", 30000);
        System.out.println(kamar1.getNomorKamar());
        System.out.println(kamar1.getTipeKamar());
        System.out.println(kamar1.getHargaKamar());
        System.out.println(kamar1.tampilkanInfoKamar());
        System.out.println("=======================");

        Reservasi reservasi1 = new Reservasi("18 Juni 2023", "20 Juni 2023");
        Reservasi reservasi2 = new Reservasi("08 Juni 2023", "30 Juni 2023");
        reservasi1.tambahTamu(tamu1);
        reservasi1.tambahTamu(tamu2);
        reservasi1.tampilkanTamu();
        reservasi1.tambahKamar(kamar1);
        reservasi1.tambahKamar(kamar2);
        reservasi1.tampilkanKamar();
        System.out.println(reservasi1.getTanggalCheckIn());
        System.out.println(reservasi1.getTanggalCheckOut());
        System.out.println(reservasi1.tampilkanInfoReservasi());
        System.out.println("=======================");


        Hotel hotel = new Hotel("best Hotel");

        hotel.tambahKamar(kamar1);
        hotel.tambahKamar(kamar2);
    }
}

class Tamu {
    private String nama;
    private String id;
    private String email;

    public Tamu(String nama, String id, String email) {
        this.nama = nama;
        this.id = id;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String tampilkanInfoTamu() {
        return nama + " " + id + " " + email;
    }

   
    public String toString() {
        return tampilkanInfoTamu();
    }
}

class Kamar {
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

class Reservasi {
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
        info += t.tampilkanInfoTamu() + "\n";
    }
    for (Kamar k : kamars) {
        info += k.tampilkanInfoKamar() + "\n";
    }
    info += "Check-in: " + tanggalCheckIn + "\n";
    info += "Check-out: " + tanggalCheckOut;
    return info;
	}
}


class Hotel {
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
        
       


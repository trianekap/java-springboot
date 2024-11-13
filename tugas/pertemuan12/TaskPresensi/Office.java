import java.util.*;

public class Office{
    private String namaOffice;
    private List<Presensi> jamMasukList;
    private List<Presensi> jamKeluarList;


    public Office(String namaHotel){
        this.namaOffice = namaOffice;
        this.jamMasukList = new ArrayList<>();
        this.jamKeluarList = new ArrayList<>();
    }

    public String getNamaOffice(){
        return namaOffice;
    }

    public List<Presensi> getJamMasukList(){
        return jamMasukList;
    }

    public void setJamMasukList(List<Presensi> jamMasukList){
        this.jamMasukList = jamMasukList;
    }

    public List<Presensi> getJamKeluarList(){
        return jamKeluarList; 
    }

    public void setJamKeluarList(List<Presensi> jamKeluarList){
        this.jamKeluarList = jamKeluarList;
    }

    public void tambahJamMasuk(Presensi jamMasuk){
        jamMasukList.add(jamMasuk);   
    }

    public void tambahJamKeluar(Presensi jamKeluar){
        jamKeluarList.add(jamKeluar);   
    }

    public String tampilkanInfoAbsensi(){
     String info = "";
     for (Presensi jm : jamMasukList){
         info += jm.tampilkanJamMasuk() + " ";
     }
     for (Presensi jm : jamKeluarList){
        info += jm.tampilkanJamKeluar() + " ";
     }
     info += "Jam-Masuk: " + jamMasukList + "\n";
     info += "Jam-Keluar: " + jamKeluarList;
     return info;
    }
}
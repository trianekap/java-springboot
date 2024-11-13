import java.util.*;

public class Presensi{
	private String jamMasuk;
	private String jamKeluar;

	public Presensi(String jamMasuk, String jamKeluar){
	this.jamMasuk = jamMasuk;
	this.jamKeluar = jamKeluar;
	}

	public String getJamMasuk() {
		return jamMasuk;
	}

	public void setJamMasuk(String jamMasuk) {
		this.jamMasuk = jamMasuk;
	}

	public String getJamKeluar() {
		return jamKeluar;
	}

	public void setJamKeluar(String jamKeluar) {
		this.jamKeluar = jamKeluar;
	}

	public String tampilkanJamMasuk(){
		return "jam masuk: " + jamMasuk;
	}

	public String tampilkanJamKeluar(){
		return "jam keluar: " + jamKeluar; 
	}


}




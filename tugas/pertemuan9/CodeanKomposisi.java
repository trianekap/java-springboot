import java.util.ArrayList;

public class CodeanKomposisi{
	public static void main(String []args){
		Mahasiswa mahasiswa1 = new Mahasiswa("John Doe", 20, "123 Jalan Utama");

		Kampus kampus = new Kampus("Kampus XYZ");
		kampus.tambahMahasiswa(mahasiswa1);

		System.out.println(mahasiswa1.nama);
		System.out.println(mahasiswa1.umur);
		System.out.println(mahasiswa1.alamat);
		
	}
}

class Mahasiswa {
	String nama;
	int umur;
	String alamat;

	public Mahasiswa(String nama, int umur, String alamat){
		this.nama = nama;
		this.umur = umur;
		this.alamat = alamat;
	}
}

class Kampus {
	String nama;
	ArrayList<Mahasiswa> mahasiswas;

	public Kampus(String nama){
		this.nama = nama;
		mahasiswas = new ArrayList<>();
	}

	public void tambahMahasiswa(Mahasiswa mahasiswa){
		mahasiswas.add(mahasiswa);
	}

	public void hitungMahasiswa(){
		for (Mahasiswa m : mahasiswas) {
			System.out.println( m.nama + " " + m.umur + " " + m.alamat);
		}
	}

}



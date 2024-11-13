//inner class non static
//kelas luar
 class KelasLuar {

	//kelas dalam ke1
		private class Mobil{
		private String merk = "SUZUKI";
		private float kecepatan = 360.0f;
		private void jalankan(){
			System.out.println("Merk Mobil: " + merk);
			System.out.println("Kecepatan Mobil : " + kecepatan);
		}
	}

	//kelas dalam kedua
		private class Pengguna{
		private String nama = "Wildan";
		private int umur = 19;
		private void identitas(){
			System.out.println("Nama Saya : " + nama);
			System.out.println("Usia Saya : " + umur);
		}
	}

	public class CobaInnerOuter{
	public static void main(String []args){
		//membuat instance dari kelas luar
		KelasLuar outerclass = new KelasLuar();
		//membuat instance dari KelasDalam (mobil)
		KelasLuar.Mobil data1 = outerclass.new Mobil();
		//membuat instance dari kelas dalam (pengguna)
		KelasLuar.Pengguna data2 = outerclass.new Pengguna();
	
	//menampilkan hasil output
		System.out.println("===== DATA DARI CLASS MOBIL =====");
		data1.jalankan();
		System.out.println("===== DATA DARI CLASS PENGGUNA =====");
		data2.identitas();

	}
  }
}
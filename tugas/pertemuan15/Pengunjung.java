public class Pengunjung{
	private String id;
	private String nama;
	private String kendaraan; 
	private int parkirMasuk;
	private int parkirKeluar;


	public Pengunjung(String id, String nama, String Kendaraan){
		this.id = id;
		this.nama = nama;
		this.kendaraan = kendaraan;
		this.parkirMasuk = parkirMasuk;
		this.parkirKeluar = parkirKeluar;
	}

	public String getId(){
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNama(){
		return nama;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getKendaraan(){
		return kendaraan;
	}

	public void setKendaraan(String kendaraan){
		this.kendaraan = kendaraan;
	}


}
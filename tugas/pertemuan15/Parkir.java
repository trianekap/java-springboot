public class Parkir{
	private int parkirMasuk;
	private int parkirKeluar;
	public int biayaParkir = 0;

	public Parkir(int parkirMasuk, int parkirKeluar){
		this.parkirMasuk = parkirMasuk;
		this.parkirKeluar = parkirKeluar;
	}


	public int getParkirMasuk(){
		return parkirMasuk;
	}

	public void setParkirMasuk(int parkirMasuk){
		this.parkirMasuk = parkirMasuk;
	}

	public int getParkirKeluar(){
		return parkirKeluar;
	}

	public void setParkirKeluar(int parkirKeluar){
		this.parkirKeluar = parkirKeluar;
	}

	public int biayaParkir(int parkirMasuk, int parkirKeluar){
		int waktuParkir = parkirKeluar - parkirMasuk;
		if (waktuParkir <= 2){
			biayaParkir = 5000;
		} else if (waktuParkir > 2){
			biayaParkir = 5000 + ((waktuParkir - 2) * 2000);
		}
		return biayaParkir;
	}
}
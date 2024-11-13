public class Pemburu extends Pahlawan{
	private int jumlahPanah;


	public Pemburu(String nama, String keterampilan, int jumlahPanah){
		super(nama, keterampilan);
		this.jumlahPanah = jumlahPanah;
	}


	 public void tampilkanKeterampilan(){
	 	System.out.println("jumlah panah : " + jumlahPanah);
	 }

}


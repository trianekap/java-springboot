public class Kesatria extends Pahlawan{
	private int jumlahPedang;


	public Kesatria(String nama, String keterampilan, int jumlahPedang){
		super(nama, keterampilan);
		this.jumlahPedang = jumlahPedang;
	}

	 public void tampilkanKeterampilan(){
	 	System.out.println("jumlah pedang : " + jumlahPedang);
	 }
}
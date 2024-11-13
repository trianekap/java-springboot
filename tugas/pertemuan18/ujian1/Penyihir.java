public class Penyihir extends Pahlawan{

	private int jumlahMantra;


  	public Penyihir(String nama, String keterampilan, int jumlahMantra){
	 	super(nama, keterampilan);
	 	this.jumlahMantra = jumlahMantra;
	 }

	 public void tampilkanKeterampilan(){
	 	System.out.println("jumlah mantra : " + jumlahMantra);
	 }



}

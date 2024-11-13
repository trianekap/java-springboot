public class Bayar{
	public static void main(String []args){
		Pengunjung pengunjung1 = new Pengunjung("001","john","matic");
		Parkir parkir1 = new Parkir(10,14);
		parkir1.biayaParkir(10,14); 
		System.out.println("Biaya parkirnya : Rp." + parkir1.biayaParkir);
	}
} 
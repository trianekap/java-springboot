public class MainTamu{
	public static void main(String []args){
		Tamu tamu = new Tamu("John","CUS_1","john@gmail.com");
		System.out.println(tamu.getNama());
		System.out.println(tamu.getId());
		System.out.println(tamu.getEmail());
		System.out.println(tamu.tampilkanInfoTamu());

		// Kamar kamar = new Kamar("10","melati",100000);
		// System.out.println(kamar.getNomorKamar());
		// System.out.println(kamar.getTipeKamar());
		// System.out.println(kamar.getHargaKamar());
		// System.out.println(kamar.tampilkanInfoKamar());

		// Reservasi reservasi = new Reservasi("18 juni 2023","20 Juni 2023");

	}
}

class Tamu {
	private String nama;
	private String id;
	private String email;
	public String tampilkanInfoTamu;

	public Tamu(String nama, String id, String email){
		this.nama = nama;
		this.id = id;
		this.email = email;
	}


	public String getNama(){
		return nama;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String tampilkanInfoTamu(){
		return nama + " " + id + " " + " " + email;
	}

}
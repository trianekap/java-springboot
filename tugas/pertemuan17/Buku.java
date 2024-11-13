public class Buku{
	private String namaBuku;
	private String jenisBuku;
	private int jumlahBuku;

	public Buku(String namaBuku, String jenisBuku, int jumlahBuku){
		this.namaBuku = namaBuku;
		this.jenisBuku = jenisBuku;
		this.jumlahBuku = jumlahBuku;
	}


	public String getNamaBuku(){
		return namaBuku;
	}

	public String getJenisBuku(){
		return jenisBuku;
	}

	public int getJumlahBuku(){
		return jumlahBuku;
	}
}
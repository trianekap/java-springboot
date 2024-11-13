import java.util.Scanner;

class Perpustakaan{
	public static Scanner input = new Scanner(System.in);
	public static void main(String []args){
		Buku[] daftarBuku = {
			new Buku("java","ilmu",100),
			new Buku("komik","fiksi",50),
			new Buku("hujan","novel",30),
			new Buku("majalah","hiburan",50),
			new Buku("dongeng","cerita",70),
		};


		System.out.println("Daftar Buku yang tersedia : "); 
		for (int i = 0; i < daftarBuku.length ; i++ ) {
			System.out.println("nama buku : " + daftarBuku[i].getNamaBuku() + 
				"\tjenis buku : \t" + daftarBuku[i].getJenisBuku() + "\tjumlah buku yang tersedia : \t" 
				+ daftarBuku[i].getJumlahBuku());
		}

		int jumlahBukuDipinjam;

		int pilihMenu;
		boolean selesai = false;

		while(selesai == false){
			System.out.println("Masukkan kode buku yang ingin dipinjam : ");
			pilihMenu = input.nextInt();

		switch (pilihMenu){
			case 0:
			selesai = true;
			break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:


			System.out.println("Masukkan jumlah Buku " + daftarBuku[pilihMenu-1].getNamaBuku() + " yang ingin dipinjam : ");	
			Scanner input = new Scanner(System.in);
			int jumlah = input.nextInt();

			int totalBukuDipinjam = 0;
			totalBukuDipinjam += daftarBuku[pilihMenu-1].getJumlahBuku() - jumlah;

			System.out.println(totalBukuDipinjam);

			// System.out.println("Daftar Buku yang tersedia : "); 
			// for (int i = 0; i < daftarBuku.length ; i++ ) {
			// System.out.println("nama buku : " + daftarBuku[i].getNamaBuku() + 
			// 	"\tjenis buku : \t" + daftarBuku[i].getJenisBuku() + "\tjumlah buku yang tersedia : \t" 
			// 	+ daftarBuku[i].getJumlahBuku());


		}


		}
	 }

	}
// }
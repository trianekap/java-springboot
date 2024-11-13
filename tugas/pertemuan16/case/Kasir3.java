import java.util.Scanner;

public class Kasir3{
	public static void main(String []args){
		
		Barang[] listBarang = {
			new Barang("indomie", 3500.0),
			new Barang("goodday", 1500.0),
			new Barang("chiki", 2000.0),
			new Barang("roti", 10500.0),
			new Barang("beras", 14000.0),
		};

		for (int i = 0; i < listBarang.length ; i++ ) {
			System.out.println("Nama Barang : " + listBarang[i].getNamaBarang() + " Rp." + listBarang[i].getHargaBarang());			
		}

		double totalBayar = 0;

	  	int pilihMenu;
	  	boolean selesai = false;

	  	while(!selesai){
	  		Scanner inputMenu = new Scanner(System.in);
	  		System.out.println("Masukkan kode barang yang ingin dibeli : ");
	  		pilihMenu = inputMenu.nextInt();

	  		switch(pilihMenu){
	  			case 0:
	  			selesai = true;
	  			break;
	  			case 1:
		  		case 2:
		  		case 3:
		  		case 4:
		  		case 5:

	  			System.out.println("Masukan jumlah " + listBarang[pilihMenu-1] + " yang dibeli: ");
	  			Scanner inputJumlah = new Scanner(System.in);

	  			int jumlah = inputJumlah.nextInt(); 
	  			totalBayar += listBarang[pilihMenu-1].getHargaBarang() * jumlah;
	  			break;
	  		default:
	  			System.out.println("menu yang anda pilih tidak ada");
	  			break;
	  		}
	  	}

	  	 
		System.out.println("total yang harus anda bayar : Rp." + totalBayar);

		double kembalian;
		double uangDiberikan; 

		Scanner inputUang = new Scanner(System.in);
		System.out.print("Masukan uang yang diberikan : Rp.");
		uangDiberikan = inputUang.nextDouble();


		kembalian = uangDiberikan - totalBayar;
		if (kembalian < 0) {
			System.out.println("uang anda kurang!!!");
		} else if (kembalian == 0){
			System.out.println("uang anda pas!!!");
		} else {
			System.out.println("kembalian anda : Rp." + kembalian);
		}

	}
}
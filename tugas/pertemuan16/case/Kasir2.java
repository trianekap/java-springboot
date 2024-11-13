import java.util.Scanner;

public class Kasir2{
	public static void main(String []args){
		String[] namaBarang = {"indomie","goodday","chiki","roti","beras"};

		double[] hargaBarang = {3000.0,1500.0,2000.0,10500.0,14000.0};

		for (int i = 0; i < namaBarang.length ; i++ ) {
			System.out.println("Nama Barang : " + namaBarang[i] + " Rp." + hargaBarang[i]);			
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

	  			System.out.println("Masukan jumlah " + namaBarang[pilihMenu-1] + " yang dibeli: ");
	  			Scanner inputJumlah = new Scanner(System.in);

	  			int jumlah = inputJumlah.nextInt(); 
	  			totalBayar += hargaBarang[pilihMenu-1] * jumlah;
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
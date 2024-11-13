import java.util.Scanner;

public class Kasir{
	public static void main(String []args){
		String[] namaBarang = {"indomie","goodday","chiki","roti","beras"};

		double[] hargaBarang = {3000.0,1500.0,2000.0,10500.0,14000.0};

		for (int i = 0; i < namaBarang.length ; i++ ) {
			System.out.println("Nama Barang : " + namaBarang[i] + " Rp." + hargaBarang[i]);			
		}

		int[] jumlahBarang = new int[namaBarang.length];

		double totalBayar = 0;

		for (int i = 0 ; i < namaBarang.length ; i++ ) {
			System.out.print("masukan jumlah " + namaBarang[i] + " yang akan di beli : ");

			Scanner input = new Scanner(System.in);
			jumlahBarang[i] = input.nextInt();

			
			totalBayar += jumlahBarang[i] * hargaBarang[i];

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
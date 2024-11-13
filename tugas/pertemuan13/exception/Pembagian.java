import java.util.Scanner;

public class Pembagian{
	public static void main(String []args){
		
		try {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Masukkan angka pertama: ");
		int angka1= scanner.nextInt();
		System.out.print("Masukkan angka kedua: ");
		int angka2 = scanner.nextInt();
		int hasil = hitungPembagian(angka1, angka2);
		System.out.println("Hasil pembagian: " + hasil);
	} catch (ArithmeticException e) {System.out.println("jangan ada pembagi 0");}

	}

	public static int hitungPembagian(int angka1, int angka2)  {
		return angka1/angka2;
		
	}
} 
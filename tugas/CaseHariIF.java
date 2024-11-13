import java.util.Scanner;

public class CaseHariIF{
	public static void main(String []args){
		Scanner masuk = new Scanner(System.in);
		int nohari;
		System.out.print("Masukan nomor hari (1.....7) : ");
		nohari = masuk.nextInt();
		

		if (nohari == 1) {
			System.out.println("Hari ke-" + nohari + " minggu");
		} else if (nohari == 2) {
			System.out.println("Hari ke-" + nohari + " senin");
		} else if (nohari == 3) {
			System.out.println("Hari ke-" + nohari + " Selasa");
		} else if (nohari == 4) {
			System.out.println("Hari ke-" + nohari + " Rabu");
		} else if (nohari == 5) {
			System.out.println("Hari ke-" + nohari + " Kamis");
		} else if (nohari == 6) {
			System.out.println("Hari ke-" + nohari + " Jumat");
		} else if (nohari == 7) {
			System.out.println("Hari ke-" + nohari + " Sabtu");
		} else {
			System.out.println("nomor hari yang dimasukan salah!!!");
		}
	}
}
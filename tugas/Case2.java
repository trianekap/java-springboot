import java.util.Scanner;

public class Case2{
	public static void main(String []args){
		int angka;
		Scanner input = new Scanner(System.in);

		System.out.print("Masukan angka : ");
		angka = input.nextInt();


		for ( int i = 1; i <= angka ; i++ ) {
			if (i % 4 == 0) {
				System.out.print("OK ");
			} else {
				System.out.print(i + " ");
			}
		}
	}
}
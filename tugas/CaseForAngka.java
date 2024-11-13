import java.util.Scanner;

public class CaseForAngka{
	public static void main(String []args){

		Scanner input = new Scanner(System.in);
		int angka;

		System.out.print("Masukan angka : " );
		angka = input.nextInt();

		for (int i = 1; i <= angka; i++){
			for (int j = 1 ; j <= i; j++){
				System.out.print(i + " ");
			}

			System.out.println("");
		}
	}
}
import java.util.Scanner;

public class Case1{
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int angka; 
		int totalAngka = 0;


		do {

			System.out.println("Masukan angka : ");
			angka = input.nextInt();

			totalAngka += angka;

			System.out.println("total angka : " + totalAngka);	

			if (totalAngka > 80){

			System.out.println("Selesai");
		  }
		} while (totalAngka <= 80);
		
	}
}
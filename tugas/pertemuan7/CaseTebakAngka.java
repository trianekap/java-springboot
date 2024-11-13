import java.util.Scanner;
import java.lang.Math;
class CaseTebakAngka {
	public static void main(String []args){
		Scanner scan = new Scanner(System.in);
		int input; 
		int kesempatan = 7;

		int angkaDitebak = (int)(Math.random()*101);
		
		for (int i = 1; i <= kesempatan; i++) {
			System.out.println("Masukkan angka : ");
			input = scan.nextInt();

			if(input == angkaDitebak){
				System.out.println("anda menang");
				break;
			} else if(input < angkaDitebak){
				System.out.println("angka terlalu kecil");
			} else if (input > angkaDitebak){
				System.out.println("angka terlalu besar");
			} 
			if(i == kesempatan){
				System.out.println("kesempatan habis");
			}
	  }
   }
}
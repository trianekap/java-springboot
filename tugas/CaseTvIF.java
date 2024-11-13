import java.util.Scanner;

public class CaseTvIF{
	public static void main (String []args){
		Scanner masuk = new Scanner(System.in);
		int pil;
		System.out.print("Masukan pilihan : ");
		pil = masuk.nextInt();

		if (pil == 1) {
			System.out.println("RCTI");
		} else if (pil == 2) {
			System.out.println("SCTV");
		} else if (pil == 3) {
			System.out.println ("TPI");
		} else if (pil == 4) {
			System.out.println ("INDOSIAR");
		} else if (pil == 5) {
			System.out.println ("TRANS7");
		} else if (pil == 6) {
			System.out.println("TVONE");
		} else if (pil == 7) {
			System.out.println ("METRO");
		} else if (pil == 8) {
			System.out.println("GLOBAL");
		} else if (pil == 9) {
			System.out.println("TRANS7");
		} else if (pil == 10) {
			System.out.println("TVRI"); 
		} else {
			System.out.println("Salah masukan pilihan !!!");
		}
	}
}
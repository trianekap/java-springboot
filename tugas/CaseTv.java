import java.util.Scanner;

public class CaseTV{
	public static void main (String []args){
		Scanner masuk = new Scanner(System.in);
		int pil;
		System.out.print("Masukan pilihan : ");
		pil = masuk.nextInt();
		switch (pil) {
		case 1: System.out.println("RCTI"); break;
		case 2: System.out.println("SCTV"); break;
		case 3: System.out.println("TPI"); break;
		case 4: System.out.println("INDOSIAR"); break;
		case 5: System.out.println("TRANS7"); break;
		case 6: System.out.println("TV ONE"); break;
		case 7: System.out.println("METRO"); break;
		case 8: System.out.println("GLOBAL"); break;
		case 9: System.out.println("TRANS7"); break;
		case 10: System.out.println("TVRI"); break;
		default: System.out.println("salah masukan pilihan!!!");
			break;
		}
	}
}

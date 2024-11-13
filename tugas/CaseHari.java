import java.util.Scanner;

public class CaseHari{
	public static void main(String []args){
		Scanner masuk = new Scanner(System.in);
		int nohari;
		System.out.print("Masukan nomor hari (1.....7) : ");
		nohari = masuk.nextInt();
		switch(nohari){
		case 1:
			System.out.println("Hari ke -" + nohari + " : adalah minggu");
			break;
		case 2:
			System.out.println("Hari ke-" + nohari + " : adalah senin");
			break;
		case 3:
			System.out.println("Hari ke-" + nohari + " : adalah selasa");
			break;
		case 4:
			System.out.println("Hari ke-" + nohari + " : adalah rabu");
			break;
		case 5:
			System.out.println("Hari ke-" + nohari + " : adalah kamis");
			break;
		case 6:
			System.out.println("Hari ke-" + nohari + " : adalah jumat");
			break;
		case 7:
			System.out.println("Hari ke-" + nohari + " : adalah sabtu");
			break;
		default:
			System.out.println("Tidak terdapat nama hari ke-" + nohari);
		}
	}
}
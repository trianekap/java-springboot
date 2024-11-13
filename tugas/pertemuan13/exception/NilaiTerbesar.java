import java.util.Scanner;

public class NilaiTerbesar {

	public static void main(String []args){
		double angka1 = 90;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Masukkan angka pertama: ");

		try {
		
		angka1 = Double.parseDouble(scanner.next());
		return;
		
		} catch(Exception e){System.out.println("ada huruf didalamnya");}
	    finally {
	    	System.out.println(angka1); // ini akan tetap dijalankan karena finally
	    }

	    System.out.print("Masukkan angka kedua: ");
	    double angka2 = scanner.nextDouble();
		System.out.print("Masukkan angka ketiga: ");
		double angka3 = scanner.nextDouble();

		double terbesar = angka1;
		if (angka2 > terbesar){
			terbesar = angka2;
		}
		if (angka3 > terbesar){
			terbesar = angka3;
		}

		System.out.println("Angka terbesar adalah: " + terbesar);
	}
}
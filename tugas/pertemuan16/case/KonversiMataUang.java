import java.util.Scanner;
public class KonversiMataUang{
	public static Scanner input = new Scanner(System.in);
	public static void main(String []args){
		System.out.print("masukan nominal rupiah : ");
		double rupiah = input.nextDouble(); 
		
		double hasil = konvertRptoReal(rupiah);
		System.out.println("hasil konversi rupiah ke real : " + hasil + " real");

		System.out.println("=========================");

		System.out.print("Masukan nominal real : ");
		double real = input.nextInt();

		double hasil2 = konvertRealtoRp(real);
		System.out.println("hasil konversi real ke rupiah : Rp." + hasil2);

	}

		public static double konvertRptoReal(double rupiah){
			double hasil = rupiah / 4327;
			return Math.round(hasil); 
		}
		
		

		public static double konvertRealtoRp(double real){
			double hasil = real * 4327;
			return Math.round(hasil);
		}
}

 	
import java.util.Scanner;

public class AkarKuadrat {
	public static void main(String []args){
		double akarKuadrat = 0;

		Scanner scanner = new Scanner(System.in);
		System.out.print("Masukkan Bilangan: ");
		double bilangan = Double.parseDouble(scanner.next());
		if (bilangan < 0){
			throw new ArithmeticException("error: tidak dapat menghitung akar kuadrat dari bilangan negatif");
		} else {
			akarKuadrat = Math.sqrt(bilangan);

			System.out.println("Akar kuadrat dari bilangan adalah: " + akarKuadrat);
		}
	} 
}

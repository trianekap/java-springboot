public class MainKalkulator{
	public static void main(String []args){

		Kalkulator kalkulator = new Kalkulator();

		int hasil1 = kalkulator.tambah(3,5);
		System.out.println("Hasil pertambahan (2 angka): " + hasil1);

		int hasil2 = kalkulator.tambah(3, 5, 7);
		System.out.println("Hasil pertambahan (3 angka): " + hasil2);
	}
}
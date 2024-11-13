import java.util.Scanner;
class TahunKabisat{
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int tahun;

		System.out.println("masukkan angka : ");
		tahun = input.nextInt();

		if(tahun % 4 == 0 || tahun % 100 == 0|| tahun % 400 == 0){
		 	System.out.println("ini tahun kabisat");
		 } else {
		 	System.out.println("bukan tahun kabisat");
		 }

	}
}
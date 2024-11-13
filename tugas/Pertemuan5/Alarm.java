import java.util.Scanner;

class Alarm{
	public static void main(String []args){
		Scanner input = new Scanner(System.in);
		int angka;
		int []alarm = {1,2,3,4,5,6,7,8,9};
		boolean ditemukan = false;

		System.out.println("Masukkan angka : ");
		angka = input.nextInt();

		for(int i : alarm){
			if (angka == i) {
				ditemukan = true;
			}
		}

		if (ditemukan == true) {
			System.out.println("alarm nyala!");
		} else {
			System.out.println("alarm mati");
		}

	}
}
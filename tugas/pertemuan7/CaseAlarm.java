import java.util.Scanner;
import java.util.Arrays;
class CaseAlarm{

	public static Scanner input = new Scanner(System.in);
	public static int angka;
	public static boolean ada = false;
	public static int pilih;

	public static int jumlahArr;
	public static int []alarm = new int[jumlahArr];

	static void MasukkanAngka(){

	System.out.println("Masukan panjang array : " );
	jumlahArr = input.nextInt();
	alarm = Arrays.copyOf(alarm, jumlahArr); 
		
	for (int i = 0; i < alarm.length ; i++ ) {
			System.out.println("Masukkan angka ke array : ");
			alarm[i] = input.nextInt();
		}

		System.out.println("tampilkan data yang ada di dalam : ");

		for (int i : alarm ) {
			System.out.print(i + " ");
		}
	}

	static void CekAlarm(){

		System.out.println("Masukkan angka untuk  di jadikan alarm : ");
		angka = input.nextInt();

		for (int i = 0 ; i < alarm.length ; i++) {
			if(angka == alarm[i]){
				ada = true;
				break;
			}
		}

		if (ada == true) {
				System.out.println("alarm nyala!!!");
			} else {
				System.out.println("alarm mati!!!");
			}
	}


	public static void main(String []args){
		while(true){
		System.out.println("Silahkan pilih menu : ");
		pilih = input.nextInt();

		switch(pilih){
		case 1:
			MasukkanAngka();
			break;
		case 2:
			CekAlarm();
			break;
		default:
			System.out.println("menu yang dimasukkan tidak ada");
			return;
		}
	  } 
	}
}
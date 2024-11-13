import java.util.Scanner;

class OnOff{
	public static void main(String []args){
		String inputan1, inputan2, inputan3;

		Scanner input1 = new Scanner(System.in);
		System.out.println("Masukkan on/off ke 1:");
		inputan1 = input1.nextLine();

		Scanner input2 = new Scanner(System.in);
		System.out.println("Masukkan on/off ke 2:");
		inputan2 = input2.nextLine();

		Scanner input3 = new Scanner(System.in);
		System.out.println("Masukkan on/off ke 3:");
		inputan3 = input3.nextLine();

		if(inputan1.equalsIgnoreCase("off") && inputan2.equalsIgnoreCase("off") && inputan3.equalsIgnoreCase("off")){
			System.out.println("hasilnya : " + "ON");
		} else {
			System.out.println("hasilnya : " + "OFF");
		}
} }
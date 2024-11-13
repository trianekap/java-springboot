import java.util.Scanner;

public class UlangWhile3{
	public static void main(String []args){
		Scanner masuk = new Scanner(System.in);

		int bil;
		bil=1;
		while (bil<=10){
			System.out.println(bil);
			bil += 2; 
		}
	}
}
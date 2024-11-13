import java.util.Scanner;

public class UlangWhile4{
	public static void main(String []args){
		char A = 'a';
		int B = 0;
		int C = 1;
		while(A<='e'){
			System.out.println("Nilai A="+A);
			System.out.println("Nilai B="+B);
			System.out.println("Nilai C="+C);
			System.out.println(" ");

			A++;
			B=B+5;
			C=C*10;
		}
	} 
}
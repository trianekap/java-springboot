
public class CaseFibonacci {


	static void fibonacciFor(){
			int num1 = 1;
			int num2 = 1;
			int n = 10;

			for (int i = 0; i < n; i++){
				
				
				System.out.println(num1 + " ");

				int num3 = num1 + num2;
				num1 = num2;
				num2 = num3;

			}

		}

	static void fibonacciWhile(){
			int i = 1, n=10, angka1 = 1, angka2 = 1; 


			while(i <= n){

				System.out.println(angka1 + " ");
				int angka3 = angka2 + angka1;
				angka1 = angka2;
				angka2 = angka3;

				i++;
			}	
	}

	static void fibonacciDoWhile(){
			int angka1 = 1;
			int angka2 = 1;
			int angka3;
			System.out.println(angka1);
			System.out.println(angka2);

		do
			{       
    			angka3 = angka2 + angka1;
    			System.out.println(angka3);

    			angka1 = angka2;
    			angka2 = angka3;          
			} while (angka2 < 55);
	}


	public static void main(String []args){

		System.out.println("ini dari for!");

		fibonacciFor();

		System.out.println("=============");

		System.out.println("ini dari while!");

		fibonacciWhile();

		System.out.println("=============");

		System.out.println("ini dari do while!");

		fibonacciDoWhile();


	}
}
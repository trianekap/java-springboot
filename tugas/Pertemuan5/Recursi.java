public class Recursi {

	public static void main(String []args) {
		int n = 5;

		int result = factorial(n);
		System.out.println("Faktorial dari " + n + " adalah " + result );

	}

	public static int factorial(int n){
		if (n == 0){
			return 1;
		} else {
			return n * factorial(n-1);
		}
	}
}
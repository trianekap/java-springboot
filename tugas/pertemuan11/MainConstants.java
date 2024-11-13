//interface sebagai konstanta

public class MainConstants {
	public static void main(String []args){
		System.out.println("Max Value: " + Constants.MAX_VALUE);
		System.out.println("PI " + Constants.PI);
	}
}

interface Constants {
	int MAX_VALUE = 100;
	double PI = 3.14159;
}
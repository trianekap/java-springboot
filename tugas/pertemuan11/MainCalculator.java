//menggunakan interface sebagai tipe data parameter

public class MainCalculator{
	public static void main(String []args){
		Calculator addition = new Calculator(){
			@Override
			public int calculate(int num1, int num2){
				return num1 + num2;
			}
		};

		int result = addition.calculate(5, 3);
		System.out.println("Result: " + result);
	}
}


interface Calculator{
	int calculate(int num1, int num2);
}


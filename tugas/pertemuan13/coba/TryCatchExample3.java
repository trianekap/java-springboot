public class TryCatchExample3{
	public static void main(String []args){

		try {
		int data = 50/0;//may throw exception

						//jika error terjadi pernyataan berikutnya tidak akan di ekseskusi
		System.out.println("rest of the code");//code ini tidak akan tereksekusi karena diatasnya error
		}
		
		//handling the exception
		catch(ArithmeticException e)
		{
			System.out.println(e);
		}

		
	}
}
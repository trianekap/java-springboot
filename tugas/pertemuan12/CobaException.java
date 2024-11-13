public class CobaException{
	public static void main(String []args){
	try {
		int[] num = {1,2,3};
		System.out.println(num[10]);

	} catch (Exception e){
		System.out.println("Something when wrong");
	}
		
  }
}
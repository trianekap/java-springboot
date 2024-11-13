public class cobaInterface {
	public static void main(String []args){
		Pig myPig = new Pig();
		myPig.animalSound();
		myPig.sleep();
	}
}

interface Animal{
	public void animalSound();
	public void sleep();
}

class Pig implements Animal{

	public void animalSound(){
	System.out.println("Pig says : Weeee Weeee");

  }
  	// public void sleep(){
  	// 	System.out.println("Zzzzzzzzzzz");
  	// }
}





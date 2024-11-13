//interface dengan satu method

public class MainGreeting{
	public static void main(String []args){
		Greeting greeting = new Greeting(){
		@Override
		public void greet(){
			System.out.println("Hello!");
		}
	};

	greeting.greet();
   }
}

interface Greeting{
	void greet();
}

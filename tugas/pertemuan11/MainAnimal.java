//interface inheritance


public class MainAnimal {
	public static void main(String []main){
		Bird bird = new Sparrow();
		bird.sound();
		bird.fly();
	}
}

interface Animal{
	void sound();
}

interface Bird extends Animal{
	void fly();
}

class Sparrow implements Bird{
	@Override
	public void sound() {
		System.out.println("Chirp chirp!");
	}

	@Override
	public void fly() {
		System.out.println("Sparrow is flyng...");
	}
}


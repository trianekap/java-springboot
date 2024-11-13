class Polymorphism{
	public static void main(String []args){
		Animal myAnimal = new Animal();
		Pig myPig = new Pig();
		Dog myDog = new Dog();
		myAnimal.animalSound();
		myPig.animalSound();
		myDog.animalSound();
	}
}



class Animal{
	public void animalSound(){
		System.out.println("The animal make a sound");
	}
}

class Pig extends Animal{
	public void animalSound(){
		System.out.println("The pig say : wee wee");
	}
}

class Dog extends Animal{
	public void animalSound(){
		System.out.println("The dog say : bowww wowww");
	}
}
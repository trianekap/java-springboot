class Vehicle{
	protected brand = "toyota";
	public void klakson(){
		System.out.println("tottttttttttt");
	}
}

class Car extends Vehicle{
	private String modelName = "avanza";
	public static voin main(String []args){
		Car myCar = new Car;
		myCar.klakson();
		System.out.println(myCar.brand + " " + myCar.modelName);
	}
}
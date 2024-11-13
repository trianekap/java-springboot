//interface dengan beberapa method

public class MainShape{
	public static void main(String []args){
		Shape shape = new Circle();
		shape.draw();
		System.out.println("Area: " + shape.calculateArea());
	}
}

interface Shape{
	void draw();
	double calculateArea();
}

class Circle implements Shape{
	@Override
	public void draw(){
		System.out.println("Drawing a circle...");
	}

	@Override
	public double calculateArea(){
		double radius = 5.0;
		return Math.PI * radius * radius;
	}
}
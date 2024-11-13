public class Main{
	public static void main(String []args){
	Person myObj = new Person();
	myObj.name = "Johnnnn edward john";
	System.out.println(myObj.name);
	}
}


class Person{
	private String name;
}

public String getName(){
	return name;
}

public void setName(String newName){
	this.name = newName;
}


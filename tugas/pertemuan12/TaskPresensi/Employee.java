class Employee {
	private String id;
	private String name;
	private String email;

	Employee(String id, String name, String email){
		this.id = id;
		this.name = name;
		this.email = email;
	} 

	public String getId(String id){
		return id;
	}

	public void setId(){
		this.id = id;
	}

	public String getName(String name){
		return name;
	}

	public void setName(){
		this.name = name;
	}

	public String getEmail(String email){
		return email;
	}

	public void setEmail(){
		this.email = email;
	}

	public String tampilkanInfoEmployee() {
        return " nama: " + name + " id: " + id + " email: " + email;
    }

}
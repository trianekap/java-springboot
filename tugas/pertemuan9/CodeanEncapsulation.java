class CodeanEncapsulation{
	public static void main(String []args){
		Lingkaran lingkaran = new Lingkaran(5.0, "merah");
		System.out.println(lingkaran.getRadius());
		lingkaran.setRadius(-2.0); // ini tidak akan mengubah nilai radius karena validasi
		lingkaran.setRadius(2.0);
		System.out.println(lingkaran.getRadius());
	}
}

class Lingkaran {
	private double radius;
	private String warna;

	//constructor
	public Lingkaran(double radius, String warna){
		this.radius = radius;
		this.warna = warna;
	}

	//setter 
	public void setRadius(double radius) {
		//validasi
		if(radius > 0) {
			this.radius = radius;
		} else {
			System.out.println("Nilai radius tidak valid");
		}
	}

	//getter
	public double getRadius(){
		return radius;
	}
}


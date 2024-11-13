//penggunaan Konstruktor pada subclass
class ProgramHewan{
	public static void main(String []args){
		Kucing kucing = new Kucing("anggora");
		kucing.suara();
	}
}

class Hewan{
	String jenis;

	Hewan(String jenis) {
		this.jenis = jenis;
	}

	void suara(){
		System.out.println("Hewan bersuara...");
	}
}

class Kucing extends Hewan {
	Kucing(String jenis){
		super(jenis);
	}

	void suara(){
		System.out.println("Hewan bersuara: Meong!");
	}
}
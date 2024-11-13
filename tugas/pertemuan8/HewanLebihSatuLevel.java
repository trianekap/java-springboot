//pewarisan lebih dari satu level
class HewanLebihSatuLevel{
	public static void main(String []args){
		Mamalia lumba = new Mamalia();
		lumba.berjalan();
		lumba.makan();

		Gajah gajah = new Gajah();
		gajah.berjalan();
	}
}


class Hewan{
	void makan() {
		System.out.println("Hewan makan...");
	}
}

class Mamalia extends Hewan{
	void berjalan() {
		System.out.println("Mamalia Berjalan...");
	}
}

class Gajah extends Mamalia{
	void bersuara(){
		System.out.println("Gajah bersuara: Trompet");
	}
}
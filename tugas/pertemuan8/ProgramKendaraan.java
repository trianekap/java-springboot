//pewarisan (superclass dan subclass)
public class ProgramKendaraan{
	public static void main(String []args){
		Mobil k = new Mobil();
		k.Bergerak();
	}
}

class Kendaraan{
	void Bergerak() {
		System.out.println("Kendaraan bergerak . . .");
	}
}

class Mobil extends Kendaraan{
	void Bergerak() {
		System.out.println("Mobil bergerak dengan 4 roda");
	}

	void Klakson() {
		System.out.println("tinnnnnn... tinnnnnnn...");
	}
}
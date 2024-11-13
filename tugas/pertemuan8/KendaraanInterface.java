//Pewarisan antar interface
interface Kendaraan {
	void bergerak();
}

class Mobil implements Kendaraan{
	public void bergerak(){
		System.out.println("Mobil bergerak...");
	}
}

class Sepeda implements Kendaraan{
	public void bergerak(){
		System.out.println("Sepeda bergerak...");
	}
}
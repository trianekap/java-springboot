//task bangun datar inheritance
public class TaskBangunDatar{
public static void main(String []args){
	Persegi persegi1 = new Persegi(4);
	persegi1.Keliling(5);
	persegi1.Luas(5);

	Segitiga segitiga1 = new Segitiga(5);
	segitiga1.Keliling(5);
	segitiga1.Luas(20,10);

	Lingkaran lingkaran1 = new Lingkaran(14);
	lingkaran1.Keliling(14);
	lingkaran1.Luas(7);

}}

class BangunDatar{
    public int Luas(){
    	return 0;
    }

    public int Keliling(){
    	return 0;
    }
}

class Persegi extends BangunDatar {

	private int sisi;

	Persegi(int sisi){
		this.sisi = sisi;
	} 

	static int Luas(int sisi){
		int luas = sisi * sisi;
		System.out.println("Luas persegi: " + luas);
		return luas;
	}

	static int Keliling(int sisi){
		int keliling = sisi * 4;
		System.out.println("Keliling persegi: " + keliling);
		return keliling;
	}
}

	class Lingkaran extends TaskBangunDatar {
		private int r;
		private double phi = 22/7;

		Lingkaran(int r){
			this.r = r;
		}

		int Keliling(int r){
			int keliling = (int)((phi * 2) * r);
			System.out.println("Keliling Lingkaran = " + keliling);
			return keliling;
		}

		int Luas(int diameter){
			int luas = (int)(phi * r * r);
			System.out.println("Luas Lingkaran = " + luas);
			return luas;
		}

	}

class Segitiga extends TaskBangunDatar {
	private int sisi;
	private int alas;
	private int tinggi;

	Segitiga(int sisi){
		this.sisi = sisi;
	}

	Segitiga(int alas, int tinggi){
		this.alas = alas;
		this.tinggi = tinggi;
	}

	static int Luas(int alas, int tinggi){
		int luas = (int)(0.5 * alas) * tinggi;
		System.out.println("Luas Segitiga = " + luas);
		return luas;
	}

	static int Keliling(int sisi){
		int Keliling = sisi + sisi + sisi;
		System.out.println("Keliling segitiga = " + Keliling);
		return Keliling;
	}  
}
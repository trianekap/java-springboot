//Pewarisan dengan Constructor Chaining
public class TaskOrang{
	public static void main(String[] args){
		Mahasiswa mahasiswa1 = new Mahasiswa("john","12345");
		System.out.println(mahasiswa1.nama + " " + mahasiswa1.nim);
	}
}

class Orang{
	String nama;

	Orang(String nama){
		this.nama = nama;
	}
}

class Mahasiswa extends Orang{
	String nim;

	Mahasiswa(String nama, String nim){
		super(nama);
		this.nim = nim;
	}
}




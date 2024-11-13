//Menandai Metode yang tidak dapat diubah
class ProgramBentuk{
	public static void main(String []args){
		Lingkaran lingkaran = new Lingkaran();
		System.out.println(lingkaran);
	}
}


class Bentuk{
	final void gambar(){
		System.out.println("Menggambar bentuk...");
	}
}

class Lingkaran extends Bentuk{
	/*tidak dapat mengubah atau meng-override 
	metode gambar() karena sudah ditandai final*/
}
//menggunakan super untuk mengakses superclass
class ProgramBangunDatar{
	 public static void main(String[] args){
	 	PersegiPanjang p = new PersegiPanjang(9, 6);
	  	p.luas();  
	  	p.luasSuper();
	}
}

class BangunDatar{ 
	void luas(){
  	System.out.println("Menghitung luas bangun datar..."); }
}

class PersegiPanjang extends BangunDatar{
	 int panjang, lebar;
	 PersegiPanjang(int panjang, int lebar){ 
	 this.panjang = panjang;
	 this.lebar = lebar;
   }
void luas(){
  	 int luas = panjang * lebar;  
  	 System.out.println("Luas persegi panjang: " + luas);
 }
 
void luasSuper(){
	super.luas();
  }
}


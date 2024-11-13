class Tes{

	public static void main(String[] args){
		Induk test = new Anak(){
		 void method(){
		 System.out.println(x);
		 test.method();
	   }
	};
	test.method();
}

	class Induk {
		int x = 3;
		void method(){
			System.out.println("ini dari kelas induk");
		} 
	}

	class Anak extends Induk{
		int x = 8;
		void method(){
			System.out.println("ini dari kelas anak");
		}
	}
}
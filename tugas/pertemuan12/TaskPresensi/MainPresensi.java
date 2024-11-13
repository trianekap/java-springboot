import java.util.Scanner;


class MainPresensi{
		public static void main(String []args){
		
		String createUsername, createPassword,inputUsername, inputPassword;

		Scanner input1 = new Scanner(System.in);
		System.out.println("buat username anda : ");
		createUsername = input1.nextLine();

		Scanner input2 = new Scanner(System.in);
		System.out.println("buat Password anda : ");
		createPassword = input2.nextLine();

		System.out.println("Akun anda berhasil dibuat");
		System.out.println();

		Scanner input3 = new Scanner(System.in);
		System.out.println("Masukkan username anda : ");
		inputUsername = input3.nextLine();

		Scanner input4 = new Scanner(System.in);
		System.out.println("Masukkan Password anda : ");
		inputPassword = input4.nextLine();


		if (inputUsername.equals(createUsername) && inputPassword.equals(createPassword)) {
			System.out.println("Selamat anda berhasil masuk!!!");
		} else {
			System.out.println("username atau password anda salah!!!");
		}
		

        Employee emp1 = new Employee("1", "John", "john@example.com", "staff", "pelajar");
        System.out.println(emp1.getNama());
        
		       

   }
}
public class MainKaryawan{
	public static void main(String []args){
		Karyawan manager = new Manager();
		Karyawan staff = new Staff();

		System.out.println("Gaji Manager: " + manager.hitungGaji());
		System.out.println("Gaji Staff: " + staff.hitungGaji());

	}
}
public class Mage extends Hero {
// 	public static void main(String []args){
// 	Mage mage = new Mage();
// 	// mage.cek(); 

// }

	// void cek(){
	// 	System.out.println(super.skill2);
	// }


	// String attack = "petirrrrr";

	// Mage(){

	// }

	Public Mage(String nama, String skill2, int damage, String role){
		this.nama = nama;
		this.skill = skill;
		this.damage = damage;
		this.role = role;
	}

	public void nama(String nama){
		System.out.println("nama mage heronya : " + nama); 
	}

	public void skill2(String skill2){
		System.out.println("skill mage heronya : " + skill2); 
	}

	public void damage(int damage){
		System.out.println("damage mage heronya : " + damage); 
	}

	public void role(String role){
		System.out.println("role heronya : " + role);
	}

	// public void attack(){
	// 	System.out.println("attackk : " + attack);
	// }
}
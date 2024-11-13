class Marksman extends Hero {

	Marksman(String nama, String skill, int damage,String role){
		this.nama = nama;
		this.skill = skill;
		this.damage = damage;
		this.role = role;
	}

	void nama(String nama){
		System.out.println("nama tank heronya : " + nama); 
	}

	void skill(String skill){
		System.out.println("skill tank heronya : " + skill); 
	}

	void damage(int damage){
		System.out.println("damage tank heronya : " + damage); 
	}

	void role(String role){
		System.out.println("role nya adalah : " + role);
	}

	void attack(){
		System.out.println("menembakkkkkk");
	}

}

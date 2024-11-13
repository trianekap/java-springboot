class PhysicsSimulation {

	public static void main(String []args) {

		int numParticles = 10;


		for (int i = 0; i < numParticles; i++){
			for(int j = i + 1; j < numParticles; j++){
				//simulasikan interaksi antara partikel A dan B
				double distance = calculateDistance(i, j);
				System.out.println("Jarak antara partikel " + i + " dan " + j
				+ " adalah " + distance);
			}
		}
	}

	private static double calculateDistance(int particleA, int particleB){
		//lakukan perhitungan jarak antara partikel A dan B
		//menggunakan informasi dan rumus terkait pemodelan fisika
		return Math.abs(particleA-particleB);
	}
}
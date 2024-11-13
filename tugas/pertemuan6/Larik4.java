public class Larik4{
	public static void main(String args[]){
		int kuadrat[];
		kuadrat = new int[10];
		for(int i=0;i<10;i++){
			kuadrat[i]=(i+1)*(i+1);
			System.out.println("Kuadrat "+(i+1)+ " = " + kuadrat[i]);
		}
	}
}
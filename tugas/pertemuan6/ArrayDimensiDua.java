public class ArrayDimensiDua{
	public static void main(String []args){
		int [][] piksel = new int[2][3];
		//mengisi elemen tertentu
		piksel[0][0] = 70;
		piksel[0][1] = 18;
		piksel[0][2] = 45;
		piksel[1][0] = 75;
		piksel[1][1] = 66;
		piksel[1][2] = 89;
		//menampilkan elemen array
		int i,j;
		for(i=0;i<2;i++){
			for(j=0; j<3; j++){
				System.out.print(piksel[i][j] + " ");
				System.out.println("");
			}
		}
	}
}
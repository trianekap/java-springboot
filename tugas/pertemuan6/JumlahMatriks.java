import java.util.Scanner;
public class JumlahMatriks{
	Scanner masuk = new Scanner(System.in);
	public void masukData(float data[][]){
		for(int i = 0; i < 3; i++){
			for (int j = 0; j < 3; j++) {
				System.out.print( "("+(i + 1)+" , "+ (j+1) + ")" + " : ");
				data[i][j]=masuk.nextFloat();
			}
		}
	}

	public float[][] tambah(float AA[][],float BB[][]){
		float hasil[][]= new float[3][3];
		for (int i=0; i < 3; i++){
			for(int j=0; j < 3 ; j++){
				hasil[i][j] = AA[i][j] + BB[i][j];
				 
			}
		}
		return hasil;
	}

	public void tampilData(float data[][], char nama){
		for (int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				System.out.print(nama+"["+ (i + 1)+"] ["+ (j+1) + "] = " +
				data[i][j]+" ");
				System.out.println();
			}
		}
	}

	public static void main(String args[]){
		float A[][] = new float[3][3];
		float B[][] = new float[3][3];
		float C[][] = new float[3][3];
		JumlahMatriks jumlah = new JumlahMatriks();
		System.out.println("Masukkan data matriks A");
		jumlah.masukData(A);
		System.out.println("Masukkan data matriks B");
		jumlah.masukData(B);
		C = jumlah.tambah(A,B);
		jumlah.tampilData(C,'C');
	}
}
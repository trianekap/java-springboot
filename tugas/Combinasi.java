import java.util.Scanner;


public class Combinasi {

    public static void main(String[] args) {
        
    Scanner masuk = new Scanner(System.in);
    int A,B;
    float jumlah,kurang,bagi,kali,sisa;
    System.out.print("Bilangan 1 : ");
    A = masuk.nextInt();
    System.out.print("Bilangan 2 : ");
    B = masuk.nextInt();
    jumlah = A + B;
    kurang = A - B;
    kali = A*B;
    bagi = A/B;
    sisa = A%B;
    System.out.println("Hasil operator aritmatika");
    System.out.println("*************************");
    System.out.println("Jumlah = " + jumlah);
    System.out.println("kurang = " + kurang);
    System.out.println("kali = " + kali);
    System.out.println("bagi = " + bagi);
    System.out.println("sisa = " + sisa);

    masuk.close();
    }
}

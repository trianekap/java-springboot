import java.util.Scanner;

public class Konversi {
    public static void main(String[] args) {
        Scanner masuk = new Scanner(System.in);
        float m, cm, inci;
        System.out.println("Masukan ukuran dalam meter : ");
        m = masuk.nextFloat();
        cm = m * 100;
        inci = m * 100 / 2.54f;
        System.out.println("dalam ukuran cm : " + cm);
        System.out.println("dalam ukuran inci : " + inci);

        masuk.close();
    }
}

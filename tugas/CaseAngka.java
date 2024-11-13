import java.util.Scanner;

public class PolaAngka {

    public static void main(String[] args) {
        // untuk menangkap inputan dari user
        Scanner input = new Scanner(System.in);
        
        
        System.out.print("Masukkan angka: ");
        int angka = input.nextInt();
        

        for (int i = 1; i <= angka; i++) {

            for (int j = 1; j <= i; j++) {
                System.out.print(i + " ");
            }

            System.out.println();
        }

        
    }
}

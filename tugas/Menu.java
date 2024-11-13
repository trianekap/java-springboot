import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner masuk = new Scanner(System.in);
        int pil;
        System.out.println("**********************");
        System.out.println("Silahkan pilih menu : ");
        System.out.println("**********************");
        System.out.println("1. Paket Lele");
        System.out.println("2. Paket Ayam");
        System.out.println("3. Paket Nila");
        System.out.print("Masukan Pilihan (1,2,3) = ");
        pil = masuk.nextInt();
        System.out.println("Paket yang anda pilih adalah paket " + pil);
        
        masuk.close();

    }
}

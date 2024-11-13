import java.util.Scanner;

public class CaseMenu {
    static Scanner input = new Scanner(System.in);

    static void calculate(int menuOption) {
        int x, y, z;
        if (menuOption != 5) {
            if (menuOption != 3) {
                System.out.print("Masukan x: ");
                x = input.nextInt();
            }
            if (menuOption != 2) {
                System.out.print("Masukan y: ");
                y = input.nextInt();
            }
            if (menuOption == 1 || menuOption == 3) {
                System.out.print("Masukan z: ");
                z = input.nextInt();
            }
        }

        switch (menuOption) {
            case 1:
                System.out.println("Hasil: " + ((2 * x) + (4 * y) - (7 * z)));
                break;
            case 2:
                if (x != 0) {
                    double hasil = Math.pow(x, -1);
                    System.out.println("Hasil: " + hasil);
                break;
            case 3:
                System.out.println("Hasil: " + ((y * y) + z));
                break;
            case 4:
                System.out.println("Hasil: " + ((x * x) + (y * y)));
                break;
            case 5:
                System.out.println("Exit");
                return;
            default:
                System.out.println("Pilihan tidak valid, coba lagi.");
                break;
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== MENU =====");
            System.out.println("FUN CALCULATOR!!!");
            System.out.println("1. 2X + 4Y - 7Z");
            System.out.println("2. 1/X");
            System.out.println("3. Y^2 + Z");
            System.out.println("4. X^2 + Y^2");
            System.out.println("5. EXIT");
            System.out.print("Silahkan Pilih Menu: ");
            int pilihMenu = input.nextInt();
            calculate(pilihMenu);
            if (pilihMenu == 5) break;
        }
    }
}

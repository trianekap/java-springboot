import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Kantor kantor = new Kantor();
        kantor.menu();

    }
}

class Kantor implements PresensiInterface {
    private Absensi absensi = new Absensi();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void addData() {
        absensi.addData();
    }

    @Override
    public void updateData() {
        absensi.updateData();
    }

    @Override
    public void deleteData() {
        absensi.deleteData();
    }

    @Override
    public void showData() {
        absensi.showData();
    }

    public void menu() {
        int pilihan;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Data Absensi");
            System.out.println("2. Update Data Absensi");
            System.out.println("3. Hapus Data Absensi");
            System.out.println("4. Tampilkan Data Absensi");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    addData();
                    break;
                case 2:
                    updateData();
                    break;
                case 3:
                    deleteData();
                    break;
                case 4:
                    showData();
                    break;
                case 5:
                    System.out.println("Keluar dari program.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }
}

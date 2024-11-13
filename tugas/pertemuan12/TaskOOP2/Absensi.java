import java.util.*;

public class Absensi implements PresensiInterface {
    private List<AbsensiData> absensiList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    private class AbsensiData {
        String id;
        String nama;
        String tanggalAbsen;

        AbsensiData(String id, String nama, String tanggalAbsen) {
            this.id = id;
            this.nama = nama;
            this.tanggalAbsen = tanggalAbsen;
        }

        public String toString() {
            return "ID Pegawai: " + id + ", Nama: " + nama + ", Tanggal Masuk: " + tanggalAbsen;
        }
    }

    @Override
    public void addData() {
        System.out.print("Masukkan ID Pegawai: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Nama Pegawai: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Tanggal Masuk (tgl-bulan-tahun): ");
        String tanggalAbsen = scanner.nextLine();

        absensiList.add(new AbsensiData(id, nama, tanggalAbsen));
        System.out.println("Data absensi berhasil ditambahkan.");
    }

    @Override
    public void updateData() {
        System.out.print("Masukkan ID Pegawai: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Tanggal lama (tgl-bulan-tahun): ");
        String tanggalLama = scanner.nextLine();
        System.out.print("Masukkan Tanggal baru (tgl-bulan-tahun): ");
        String tanggalBaru = scanner.nextLine();

        for (AbsensiData data : absensiList) {
            if (data.id.equals(id) && data.tanggalAbsen.equals(tanggalLama)) {
                data.tanggalAbsen = tanggalBaru;
                System.out.println("Data absensi berhasil diupdate.");
                return;
            }
        }
        System.out.println("Data absensi tidak ditemukan.");
    }

        @Override
        public void deleteData() {
        System.out.print("Masukkan ID Pegawai: ");
        String id = scanner.nextLine();
        System.out.print("Masukkan Tanggal (tgl-bulan-tahun): ");
        String tanggalAbsen = scanner.nextLine();

        for (int i = 0; i < absensiList.size(); i++) {
            AbsensiData data = absensiList.get(i);
            if (data.id.equals(id) && data.tanggalAbsen.equals(tanggalAbsen)) {
                absensiList.remove(i);
                System.out.println("Data absensi berhasil dihapus.");
                return;
            }
        }
        System.out.println("Data absensi tidak ditemukan.");
    }

    @Override
    public void showData() {
        if (absensiList.isEmpty()) {
            System.out.println("Tidak ada data absensi.");
        } else {
            for (AbsensiData data : absensiList) {
                System.out.println(data);
            }
        }
    }
}

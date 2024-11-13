public class Perpus {
//deklarasi variabel umum
String dataString;
int a=0;
int dataBulat ;
//deklarasi variabel transaksi
String nomorAnggota,namaAnggota, tanggalPinjam;
int bpinjam=0,bayar=0,denda=0,jml=0,tbayar=0, lama=0,tarif=0;
public void menu() {
int menu = 0;
//membuat method menu utama
System.out.println ("\n");
System.out.println (" ********************************");
System.out.println (" * Menu Utama: *");
System.out.println (" * -------------------------*");
System.out.println (" * 1. Daftar Buku *");
System.out.println (" * 2. Peminjaman *");
System.out.println (" * 3. Pengembalian *");
System.out.println (" * 4. Exit *");
System.out.println (" * *");
System.out.println (" ********************************");
System.out.println ("\n");
System.out.print("\n Masukkan menu pilihan anda: ");
dataString=InputKeyboard.inputString();
menu = Integer.valueOf(dataString).intValue();
switch(menu) {
case 1 :
daftarBuku();// memanggil method Daftar Buku
break;
case 2 :
peminjaman(); //memanggil method Peminjaman
break;
case 3 :
pengembalian(); //memanggil method Pengembalian
break;
case 4 :
System.out.println("Terima kasih");
System.exit(0);
break;
default :
System.out.print("Maaf, pilihan yang Anda masukkan salah!");
pilihMenu();//memanggil method pilihMenu
}
}
public void pilihMenu(){
int pilihMenu = 0;
System.out.print("**)1. Kembali ke Menu Utama 2.Keluar ");
System.out.print("\n **)Masukkan menu pilihan anda: ");
dataString=InputKeyboard.inputString();
pilihMenu = Integer.valueOf(dataString).intValue();
switch(pilihMenu) {
case 1 :
menu();
break;
case 2 :
System.out.println("Terima kasih");
System.exit(0);
break;
default :
System.out.print("Maaf, pilihan yang Anda masukkan salah!");
pilihMenu();
}
}
public void daftarBuku(){
System.out.println ("\n");
System.out.println (" DAFTAR BUKU ");
System.out.println ("------------------------------------------------------------------");
System.out.println ("|No.| Jenis | Judul | Penerbit | Tahun |");
System.out.println ("|--------------------------------------------------------------------------|");
System.out.println ("| 1. |Kamus |Kamus Ingris-Indonesia |Balai Pustaka | 2009 |");
System.out.println ("| 2 .|Kamus |Kumpulan Istilah Komputer |Media Komputindo| 2006 |");
System.out.println ("| 3. |Kamus |Kamus Basa Jawi |Semar Mesem Pres | 2009 |");
System.out.println ("| 4. |Majalah |Komputex |Elex Media | 2009 |");
System.out.println ("| 5. |Majalah |Pria Dewasa |Tarsindo | 2009 |");
System.out.println ("| 6. |Majalah |Cempaka |Suara Merdeka Pr | 2009 |");
System.out.println ("| 7. |Textbook|Panduan Java |Andi Offset | 2005 |");
System.out.println ("| 8. |Textbook|Pemrograman Delphi |Setia Kawan Pres | 2006 |");
System.out.println ("| 9. |Textbook|Text Processing |Pramadya Press | 2008 |");
System.out.println ("| 10.|Textbook|Java Fundamental |Abadi Offset | 2009 |");
System.out.println ("----------------------------------------------------------------------------");
pilihMenu();
}
public void peminjaman(){
System.out.println("");
System.out.println("----------------------------------------");
System.out.println("| PEMINJAMAN BUKU |");
System.out.println("|--------------------------------------- |");
System.out.println("| Tarif Peminjaman per minggu: |");
System.out.println("| 1. Jenis Buku Majalah = Rp.1000,- |");
System.out.println("| 2. Jenis Buku Textbook = Rp.2000,-|");
System.out.println("| 3. Jenis Buku Kamus = Rp.3000,- |");
System.out.println("----------------------------------------");
System.out.print("1. Tanggal Pinjam = ");
tanggalPinjam = InputKeyboard.inputString();
System.out.print("2. Nomor Anggota = ");
nomorAnggota = InputKeyboard.inputString();
System.out.print("3. Nama Anggota = ");
namaAnggota = InputKeyboard.inputString();
System.out.print("4. Jenis buku = ");
String dataStringJenisBuku = InputKeyboard.inputString();
System.out.print("5. Jumlah Buku = ");
String dataStringJumlahBuku = InputKeyboard.inputString();
int jumlahBuku = Integer.valueOf(dataStringJumlahBuku).intValue();
int jenisBuku = Integer.valueOf(dataStringJenisBuku).intValue();
if ( jenisBuku == 1){
tarif = 1000;
}
if ( jenisBuku == 2){
tarif = 2000;
}
if ( jenisBuku == 3){
tarif = 3000;
}
System.out.println("----------------------------------------");
System.out.println("Biaya Peminjaman = "+ (jumlahBuku * tarif) );
System.out.println("----------------------------------------");
pilihMenu();
}
public void pengembalian(){
System.out.println("");
System.out.println ("--------------------------------------------");
System.out.println ("| PENGEMBALIAN BUKU |");
System.out.println ("| (Denda keterlambatan Rp. 500 per hari) |");
System.out.println ("--------------------------------------------");
System.out.print("1. Tanggal Kembali = ");
tanggalPinjam = InputKeyboard.inputString();
System.out.print("2. Nomor Anggota = ");
nomorAnggota = InputKeyboard.inputString();
System.out.print("3. Nama Anggota = ");
namaAnggota = InputKeyboard.inputString();
System.out.print("4. Jumlah Buku = ");
String dataStringJumlahBuku = InputKeyboard.inputString();
System.out.print("5. Jumlah Hari = ");
String dataStringJumlahHari = InputKeyboard.inputString();
int jumlahBuku = Integer.valueOf(dataStringJumlahBuku).intValue();
int jumlahHari = Integer.valueOf(dataStringJumlahHari).intValue();
if ( jumlahHari > 7){
denda = jumlahHari - 7;
}
System.out.println ("--------------------------------------------");
int totalDenda = denda * 500 * jumlahBuku;
System.out.println("Total Denda = Rp "+ totalDenda );
System.out.println("");
pilihMenu();
}
}
public class Biodata {
    
    //variable instance
    private String nama;
    private String alamat;
    private String pendidikan;
    private int usia;

    //constructor
    public Biodata(String nama, String alamat, int usia, String pendidikan) {
        this.nama = nama;
        this.alamat = alamat;
        this.usia = usia;
        this.pendidikan = pendidikan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getNama(){
        return nama;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat = alamat;
    }

    public void tampilkanPendidikan(){
        System.out.println("Pendidikan\t: " + pendidikan);
    }

    static int hitungUsia(int tahunIni, int tahunLahir){
        return tahunIni - tahunLahir;
    }

    static int hitungTotalBelajar(int tahunLulus, int TahunMasuk){
        return tahunLulus - TahunMasuk;
    }

    public static void main(String[] args) {
        
        Biodata obj = new Biodata("trian", "yogyakarta", 20, "SI");
        System.out.println("### BIODATA ###");
        System.out.println();
        System.out.println("Nama\t\t: " + obj.nama);
        System.out.println("Alamat\t\t: " + obj.alamat);
        obj.tampilkanPendidikan();
        System.out.println("Usia saya\t: " + hitungUsia(2024, 2000));
        System.out.println("Lama Belajar\t: " + Biodata.hitungTotalBelajar(2024, 2020));
    
        obj.setNama("john doe");
        obj.setAlamat("Bandung");

        System.out.println(obj.nama);
        System.out.println(obj.alamat);
    
    }
}

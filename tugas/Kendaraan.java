public class Kendaraan {
    private String jenis;
    private int tahunProduksi;

    public Kendaraan (String jenis, int tahunProduksi){
        this.jenis = jenis;
        this.tahunProduksi = tahunProduksi;
    }

    public String getJenis() {
        return jenis;
    }

    public int getTahunProduksi(){
        return tahunProduksi;
    }

    public void tampilkanInfo() {
        System.out.println("Jenis Kendaraan: " + jenis);
        System.out.println("Tahun Produksi: " + tahunProduksi);
    } 

    public static void main(String []args){
        Kendaraan mobil = new Kendaraan("Sedan", 2020);
        mobil.tampilkanInfo();
    }
}

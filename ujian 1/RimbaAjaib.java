public class RimbaAjaib {
    private Pahlawan[] daftarPahlawan;
    private int indexPahlawan;

    public RimbaAjaib(int maxPahlawan) {
        this.daftarPahlawan = new Pahlawan[maxPahlawan];
        this.indexPahlawan = 0;
    }

    public void tambahPahlawan(Pahlawan pahlawan) {
        if (indexPahlawan < daftarPahlawan.length) {
            daftarPahlawan[indexPahlawan++] = pahlawan;
        } else {
            System.out.println("Daftar pahlawan sudah penuh.");
        }
    }

    public void tampilkanPahlawan() {
        System.out.println("Daftar Pahlawan:");
        for (int i = 0; i < indexPahlawan; i++) {
            System.out.println((i+1) + ". " + daftarPahlawan[i].getNama() + " - " + daftarPahlawan[i].getKeterampilan());
        }
    }

}
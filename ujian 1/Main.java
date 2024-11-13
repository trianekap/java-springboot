public class Main {
	public static void main(String []args){

		RimbaAjaib rimba = new RimbaAjaib(3);

        rimba.tambahPahlawan(new Penyihir("Agung", "Penyihir", 10));
        rimba.tambahPahlawan(new Pemburu("Siti", "Pemburu", 50));
        rimba.tambahPahlawan(new Kesatria("Budi", "Kesatria", 2));

 
        rimba.tampilkanPahlawan();
        System.out.println();

        TantanganMakhluk t1 = new TantanganMakhluk("Bertemu dengan makhluk aneh di dalam goa.");
        t1.tampilkanDeskripsi();
        new Penyihir("Agung", "Penyihir", 10).tampilkanKeterampilan();
        System.out.println();

        TantanganPuzzle t2 = new TantanganPuzzle("Pecahkan teka-teki aneh yang menghalangi jalan.");
        t2.tampilkanDeskripsi();
        new Pemburu("Siti", "Pemburu", 50).tampilkanKeterampilan();
        System.out.println();

        TantanganMisterius t3 = new TantanganMisterius("Temukan jalan keluar dari labirin misterius.");
        t3.tampilkanDeskripsi();
        new Kesatria("Budi", "Kesatria", 2).tampilkanKeterampilan();

	}
}
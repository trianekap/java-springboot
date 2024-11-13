class MainTamu{
	public static void main(String []args){
		Tamu tamu1 = new Tamu("John", "CUS_1", "john@gmail.com");
        Tamu tamu2 = new Tamu("chris", "CUS_2", "chris@gmail.com");
        System.out.println(tamu1.getNama());
        System.out.println(tamu1.getId());
        System.out.println(tamu1.getEmail());
        System.out.println(tamu1.tampilkanInfoTamu());
        System.out.println("=======================");

        Kamar kamar1 = new Kamar("10", "melati", 100000);
        Kamar kamar2 = new Kamar("12", "mawar", 30000);
        System.out.println(kamar1.getNomorKamar());
        System.out.println(kamar1.getTipeKamar());
        System.out.println(kamar1.getHargaKamar());
        System.out.println(kamar1.tampilkanInfoKamar());
        System.out.println("=======================");

        Reservasi reservasi1 = new Reservasi("18 Juni 2023", "20 Juni 2023");
        Reservasi reservasi2 = new Reservasi("08 Juni 2023", "30 Juni 2023");
        reservasi1.tambahTamu(tamu1);
        reservasi1.tambahTamu(tamu2);
        reservasi1.tampilkanTamu();
        reservasi1.tambahKamar(kamar1);
        reservasi1.tambahKamar(kamar2);
        reservasi1.tampilkanKamar();
        System.out.println(reservasi1.getTanggalCheckIn());
        System.out.println(reservasi1.getTanggalCheckOut());
        System.out.println(reservasi1.tampilkanInfoReservasi());
        System.out.println("=======================");

        Hotel hotel = new Hotel("best Hotel");

        hotel.tambahKamar(kamar1);
        hotel.tambahKamar(kamar2);

	}
}


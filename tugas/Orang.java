public class Orang {

        //variable instance
        private String nama;
        private int usia;

        //contructor
        public Orang (String nama, int usia){
            this.nama = nama;
            this.usia = usia;
        } 

        //metode mengambil nama
        public String getNama(){
            return nama;
        }

        //metode mengambil usia
        public int getUsia() {
            return usia;
        }

        //metode untuk mengubah nama
        public void setNama(String nama){
            this.nama = nama;
        }

        //metode untuk mengubah usia
        public void setUsia(int usia){
            this.usia = usia;
        }

        //metode untuk menampilkan informasi diri
        public void tampilkanInfo() {
            System.out.println("Nama : " + nama);
            System.out.println("usia : " + usia + " tahun");
        }


        public static void main(String[] args) {
            //membuat objek orang dengan menggunakan constructor
            Orang orang1 = new Orang("John Doe", 20);

            // memanggil metode untuk menampilkan informasi
            orang1.tampilkanInfo();

            //mengubah nama dan usia
            orang1.setNama("Jane smith");
            orang1.setUsia(25);

            //memanggil metode lagi setelah perubahan
            orang1.tampilkanInfo();
        }

        



}

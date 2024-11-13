public class CaseUnsurAngka {

    public static void main(String[] args) {
        

        //melakukan perulangan dari 1-100
        for (int i = 1; i <= 100; i++){

            /*melakukan pengecekan apakah di dalam iterasi
              tersebut mengandung unsur 4 dengan memanggil method
              containsDigitFour
            */ 
            if (containsDigitFour(i)) {
                System.out.print("OK ");
            } else {
                System.out.print(i + " ");
            }
        }
    }

    //method untuk mengecek ada unsur 4 atau tidak 
    static boolean containsDigitFour(int number){
            //membuat variabel yang isinya mengonvert dari integer ke string
            String numberStr = Integer.toString(number);
            //mengembalikan nilai yang di contains adalah string 4
            return numberStr.contains("4");
        }
    }



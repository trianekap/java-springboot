import java.util.Scanner;

public class KonversiSuhu {
    public static void main(String[] args) {
        Scanner masuk = new Scanner(System.in);
        int celcius, reamur;
        float fahreinhet, kelvin;
        System.out.print("Masukan derajat celcius : ");
        celcius = masuk.nextInt();
        fahreinhet = 9f/5f * celcius + 32f;
        reamur = celcius * 4/5;
        kelvin = celcius + 273;
        System.out.println("Fahreinhet : " + fahreinhet);
        System.out.println("reamur : " + reamur);
        System.out.println("kelvin : " + kelvin);

        masuk.close();
    }
}

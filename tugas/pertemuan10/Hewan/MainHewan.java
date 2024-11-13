import java.util.*;
public class MainHewan {
	public static void main(String []args){
		Hewan kucing = new Kucing();
		Hewan anjing = new Anjing();

		kucing.suara();
		anjing.suara();


		List<Hewan> hewan = new ArrayList<Hewan>();
		hewan.add(kucing);
		hewan.add(anjing);

		for (Hewan h : hewan) {
			h.suara();
		}

	}
}
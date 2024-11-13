public class EvenNumberCounter {
	public static void main(String[] args){
		CounterThread thread1 = new CounterThread(1, 50);
		CounterThread thread2 = new CounterThread(51, 100);

		thread1.start();
		thread2.start();

		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int totalEven = thread1.getCount() + thread2.getCount();
		System.out.println("Jumlah angka genap dari 1 hingga 100: " + totalEven);
	}
}

class CounterThread extends Thread{
	private int start;
	private int end;
	private int count;

	public CounterThread(int start, int end){
		this.start = start;
		this.end = end;
		this.count = 0;
	}

	@Override
	public void run() {
		for (int i = start ; i <= end ; i++) {
			if (i % 2 == 0) {
				count++;
			}
		}
	}

	public int getCount(){
		return count;
	}
}
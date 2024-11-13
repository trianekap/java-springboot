public class MultiThreadedSum{
	public static void main(String []args){
	int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int total = 0;

	int segmentSize = numbers.length/3;
	SumThread thread1 = new SumThread(numbers, 0, segmentSize);
	SumThread thread2 = new SumThread(numbers, segmentSize, 2 * segmentSize);
	SumThread thread3 = new SumThread(numbers, 2 * segmentSize, numbers.length);

	thread1.start();
	thread2.start();
	thread3.start();

	try {
		thread1.join();
		thread2.join();
		thread3.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}

	total = thread1.getSum() + thread2.getSum() + thread3.getSum();
	System.out.println("Total: " + total);
	
	}
}

class SumThread extends Thread {
	private int[] numbers;
	private int startIndex;
	private int endIndex;
	private int sum;

	public SumThread(int[] numbers, int startIndex, int endIndex){
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.sum = 0;
	}

	@Override 
	public void run(){
		for(int i = startIndex; i < endIndex; i++){
			sum += numbers[i];
		}
	}

	public int getSum(){
		return sum;
	}
} 
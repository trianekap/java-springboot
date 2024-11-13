//interface sebagai tipe data pengembalian dari method

public class MainProcessor {
	public static void main(String []args){
		DataProcessor processor = createProcessor();
		processor.process();
	}

	public static DataProcessor createProcessor() {
		return new FileProcessor();
	}
}


interface DataProcessor{
	void process();
}

class FileProcessor implements DataProcessor {
	@Override
	public void process() {
		System.out.println("Processing data from file...");
	}
}
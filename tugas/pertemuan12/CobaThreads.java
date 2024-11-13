public class CobaThreads extends Thread {
  public void run() {
    System.out.println("thread is running ");
  }

  public class Main implements Runnable {
  public void run() {
    System.out.println("This code is running in a thread");
  }
}

  public static void main(String[] args){
  	CobaThreads obj = new CobaThreads();
  	obj.start();

  }
}
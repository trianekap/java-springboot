//mengimplementasikan interface pada anonymous class

public class MainMessage {
	public static void main(String[] args){
		Message message = new Message(){
		// @Override
		public void displayMessage(){
			System.out.println("this is an anonymous message.");
		}
	};

	message.displayMessage();
  }
}

interface Message{
	void displayMessage();
}
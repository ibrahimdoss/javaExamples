package interfaces;

public class Main {

	public static void main(String[] args) {
		
		Logger[] loggers= {new SmsLogger(),new DatabaseLogger()};
		CustomerManager customerManager = new CustomerManager(loggers);
		
		Customer ibrahim= new Customer(1,"Ýbrahim","Dos");
		customerManager.add(ibrahim);

	}

}

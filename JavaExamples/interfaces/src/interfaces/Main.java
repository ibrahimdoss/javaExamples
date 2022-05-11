package interfaces;

public class Main {

	public static void main(String[] args) {
		
		Logger[] loggers= {new SmsLogger(),new DatabaseLogger()};
		CustomerManager customerManager = new CustomerManager(loggers);
		
		Customer ibrahim= new Customer(1,"�brahim","Dos");
		customerManager.add(ibrahim);

	}

}

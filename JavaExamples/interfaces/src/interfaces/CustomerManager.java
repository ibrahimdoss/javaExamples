package interfaces;

public class CustomerManager {
	
	private Logger[] loggers;
	
	public CustomerManager(Logger[] loggers) {
		
		this.loggers = loggers;
	}

	public void add(Customer customer) {
		System.out.println("M��teri Eklendi : " + customer.getFirstName());
		
		Utils.runLoggers(loggers, customer.getFirstName());
		Utils.runLoggers(loggers, customer.getLastName());
		
	}
	
	public void delete(Customer customer) {
		System.out.println("M��teri Silindi : " + customer.getFirstName());
		Utils.runLoggers(loggers, customer.getLastName());

	}
}

//Dependency Injection
//Ba��ml�l��� kald�rmak i�in dependency Injection yapt�k yani �nce private logger olu�turduk ona bi tane parametreli constructor olu�turduk.
//ard�ndan ekleme ve silme i�lemlerinde this.logger.log ile ve getfirstname ile sadece isimleri okuma i�lemi yapt�k.
// ard�ndan main k�sm�nda 		CustomerManager customerManager = new CustomerManager(new SmsLogger()); bu kodda new smslogger yerine
//database yazsak da olur, file yazsak da olur ba��ml�l��� kald�rm�� oluyoruz.


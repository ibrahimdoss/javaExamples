package interfaces;

public class CustomerManager {
	
	private Logger[] loggers;
	
	public CustomerManager(Logger[] loggers) {
		
		this.loggers = loggers;
	}

	public void add(Customer customer) {
		System.out.println("Müþteri Eklendi : " + customer.getFirstName());
		
		Utils.runLoggers(loggers, customer.getFirstName());
		Utils.runLoggers(loggers, customer.getLastName());
		
	}
	
	public void delete(Customer customer) {
		System.out.println("Müþteri Silindi : " + customer.getFirstName());
		Utils.runLoggers(loggers, customer.getLastName());

	}
}

//Dependency Injection
//Baðýmlýlýðý kaldýrmak için dependency Injection yaptýk yani önce private logger oluþturduk ona bi tane parametreli constructor oluþturduk.
//ardýndan ekleme ve silme iþlemlerinde this.logger.log ile ve getfirstname ile sadece isimleri okuma iþlemi yaptýk.
// ardýndan main kýsmýnda 		CustomerManager customerManager = new CustomerManager(new SmsLogger()); bu kodda new smslogger yerine
//database yazsak da olur, file yazsak da olur baðýmlýlýðý kaldýrmýþ oluyoruz.


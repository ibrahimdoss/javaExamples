package abstractDemo;

public class CustomerManager {
	
	private BaseDatabaseManager databaseManager;
	
	public CustomerManager(BaseDatabaseManager databaseManager) {
		this.databaseManager=databaseManager;
	}
	
	public void getCustomers() {
		databaseManager.getData();
	}
}

//Yukarıdaki işlemde önce base kısmımızda private olarak basemanager kısmımızı databasemanager olarak veriyoruz.
// sonra constructor yapıp parametre olarak veriyoruz.
//main kısmında ise 		CustomerManager customerManager=new CustomerManager(new OracleDatabaseManager());
// yukarıdaki gibi tanımlama yapıyoruz oracle veya sql diğer noktaları customermanager parametresi içine giriyoruz böylece
//bağımlılığı kaldırıyoruz.
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

//Yukar�daki i�lemde �nce base k�sm�m�zda private olarak basemanager k�sm�m�z� databasemanager olarak veriyoruz.
// sonra constructor yap�p parametre olarak veriyoruz.
//main k�sm�nda ise 		CustomerManager customerManager=new CustomerManager(new OracleDatabaseManager());
// yukar�daki gibi tan�mlama yap�yoruz oracle veya sql di�er noktalar� customermanager parametresi i�ine giriyoruz b�ylece
//ba��ml�l��� kald�r�yoruz.
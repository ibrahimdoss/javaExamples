package polymorphismDemo;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * BaseLogger[] loggers= new BaseLogger[] {new FileLogger(),new
		 * EmailLogger(),new DatabaseLogger()};
		 * 
		 * for (BaseLogger Logger : loggers) { Logger.Log("Log mesaj"); }
		 */
		
		CustomerManager customerManager = new CustomerManager(new DatabaseLogger());
		customerManager.Add();
	}

}

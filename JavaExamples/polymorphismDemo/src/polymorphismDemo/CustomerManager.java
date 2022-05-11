package polymorphismDemo;

public class CustomerManager {
	
	private BaseLogger baseLogger;
	
	public CustomerManager(BaseLogger baseLogger) {
		
		this.baseLogger = baseLogger;
	}

	public void Add() {
		System.out.println("Eklendi");
		this.baseLogger.Log("log mesajý");
		
	}
}

package GoogleAuthManager;

import Entities.concretes.User;

public class GoogleAuthManager {
	public void register(User user) {
		System.out.println("Say�n" + user.getName()+" " + user.getSurname()+"Giri� yapt�n�z.");
	}
	
	public void login(User user) {
		System.out.println("Giri� Ba�ar�s�z!!!");
	}
}

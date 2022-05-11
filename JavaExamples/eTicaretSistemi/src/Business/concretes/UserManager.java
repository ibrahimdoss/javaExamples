package Business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Business.abstracts.UserService;
import DataAccess.abstracts.UserDao;
import Entities.concretes.User;
import GoogleAuthManager.AuthService;

public class UserManager implements UserService{

	private UserDao userDao;
	private AuthService authService;
	
	public UserManager(UserDao userDao, AuthService authService) {
		super();
		this.userDao = userDao;
		this.authService = authService;
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		if (user.getPassword().length()<6) {
			System.out.println("�ifre Karakter Say�s� Yetersiz.");
			
		}
		
		final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
		final Pattern pattern= Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(user.getEmail());
		
		if (!matcher.matches()) {
			System.out.println( user.getEmail()+ "E-posta format�na uygun de�il");
		}
		
		for(User users: userDao.getAll()) {
			if (users.getEmail().equals(user.getEmail())) {
				System.out.println(user.getEmail() + "Bu e-posta kay�tl�d�r.");
			}
		}
		
		if(user.getName().length()<2 && user.getName().length()<2) {
			System.out.println("Ad ve soyad en az iki karakterden olu�mal�d�r.");
		}
	}

	@Override
	public void login(User user) {
		// TODO Auto-generated method stub
		if (user.isVerify()==true) {
			userDao.login(user);
		}else {
			System.out.println("Giri� Ba�ar�s�z");
		}
	}

	@Override
	public void confirm(User user) {
		// TODO Auto-generated method stub
		if (user.isVerify()==true) {
			userDao.confirm(user);
		}else {
			System.out.println("Mail do�rulama Ba�ar�s�z!");
		}
	}

	@Override
	public void registerWithAccount(User user) {
		// TODO Auto-generated method stub
		user.setVerify(true);
		this.authService.register(user);
	}

	@Override
	public void loginWithAccount(User user) {
		// TODO Auto-generated method stub
		if (user.isVerify()==true) {
			this.authService.login(user);
			
		}else {
			System.out.println("Hen�z Kay�t olu�mad�");
		}
	}

}

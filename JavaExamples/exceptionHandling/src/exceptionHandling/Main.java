package exceptionHandling;

public class Main {

	public static void main(String[] args) {
		
		try
		{
			int[] sayilar= new int [] {1,2,3};
			System.out.println(sayilar[5]);
			
		}catch (StringIndexOutOfBoundsException exception) {
			System.out.println(exception);
		}catch (ArrayIndexOutOfBoundsException exception) {
			System.out.println(exception);
		}catch (Exception exception) {
			System.out.println(exception +"Loglandý");
		}
		finally {
			System.out.println("Her türlü çalýþan sistem.");
		}
	}
	//yukarýdaki kodda Stringhatasý olmadýðý için array hatasý olduðu için bize
	//array hatasý dönderir. eðer ikisinin de olmadýðý bir hata ise exception çalýþýr.
	//
	
	// try ya da catch çalýþsa da çalýþmasa da finally her türlü çalýþýr.

}

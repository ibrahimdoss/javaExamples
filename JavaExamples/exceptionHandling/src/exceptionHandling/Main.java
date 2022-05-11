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
			System.out.println(exception +"Logland�");
		}
		finally {
			System.out.println("Her t�rl� �al��an sistem.");
		}
	}
	//yukar�daki kodda Stringhatas� olmad��� i�in array hatas� oldu�u i�in bize
	//array hatas� d�nderir. e�er ikisinin de olmad��� bir hata ise exception �al���r.
	//
	
	// try ya da catch �al��sa da �al��masa da finally her t�rl� �al���r.

}

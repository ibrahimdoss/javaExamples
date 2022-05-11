package abstractClasses;

public abstract class GameCalculator {
	public abstract void hesapla();
	
	public void gameOver() {
		System.out.println("Oyun Bitti");
	}
}

//abstract dedi�imiz zaman yukar�daki �rnekte gamecalculator hesapla dedik. abstract ile bunu zorunlu k�ld�k.
//yani gamecalculatoru kim extends ya da implements ederse hesapla methodunu kullanmak zorunda.
// yani override yapmak zorunda .
//abstract classlar asla newlenemez tek ba�lar�na kullan�lamazlar.
// abstract classlar her alanda i�i doldurulmas� zorunlu operasyonlar�m�z i�in kullan�r�z.

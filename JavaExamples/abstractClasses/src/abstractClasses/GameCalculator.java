package abstractClasses;

public abstract class GameCalculator {
	public abstract void hesapla();
	
	public void gameOver() {
		System.out.println("Oyun Bitti");
	}
}

//abstract dediðimiz zaman yukarýdaki örnekte gamecalculator hesapla dedik. abstract ile bunu zorunlu kýldýk.
//yani gamecalculatoru kim extends ya da implements ederse hesapla methodunu kullanmak zorunda.
// yani override yapmak zorunda .
//abstract classlar asla newlenemez tek baþlarýna kullanýlamazlar.
// abstract classlar her alanda içi doldurulmasý zorunlu operasyonlarýmýz için kullanýrýz.

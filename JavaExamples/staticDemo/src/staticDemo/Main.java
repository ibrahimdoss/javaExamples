package staticDemo;

public class Main {

	public static void main(String[] args) {
		
		ProductManager manager= new ProductManager();
		Product product = new Product();
		product.price=10;
		product.name="Mouse";
		
		manager.add(product);

	}

}

//Ara� kullan�m�nda kullan b�rak sistemlerinde static kullan�r�z.
//bir metodu static yapt���m�zda class ismi ile direkt �a��rabiliriz.

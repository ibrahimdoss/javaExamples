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

//Araç kullanımında kullan bırak sistemlerinde static kullanırız.
//bir metodu static yaptığımızda class ismi ile direkt çağırabiliriz.

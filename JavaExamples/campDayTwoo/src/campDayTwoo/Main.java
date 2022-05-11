package campDayTwoo;

import campDayTwoo.Product;
import campDayTwoo.ProductManager;

public class Main {

	public static void main(String[] args) {
		Product product1 = new Product();
		product1.setId(1); 
		product1.setCourseName("Yaz�l�m Geli�tirici Yeti�tirme Kamp�(JAVA + REACT)"); 
		product1.setInstructor("Engin Demiro�");
		product1.setPrice("�cretsiz");
		product1.setCourseProgram("1.G�n, 2.G�n, 3.G�n, 4.G�n, 5.G�n");
		
		Product[] products = {product1};
		for (Product product : products) {
			System.out.println(product.courseName);
		}
		
		ProductManager productManager=new ProductManager();
		productManager.login(product1);

	}

}

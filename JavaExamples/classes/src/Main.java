
public class Main {

	public static void main(String[] args) {
		
		Product product = new Product();
		product.setName("Laptop");
		product.setId(2);
		product.setDescription("iyi");
		product.setPrice(5000);
		product.setStockAmount(5);
		
		ProductManager productManager=new ProductManager();
		productManager.Add(product);
		System.out.println(product.getDescription());
				
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//CustomerManager customerManager=new CustomerManager();
		//customerManager.Add();
		
		/*
		 * DortIslem dortIslem =new DortIslem(); int sonuc =dortIslem.Topla(2, 4);
		 * System.out.println(sonuc);
		 */
		
	}

}


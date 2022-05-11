package youtubeSrp;

public class CustomerManager {
	
	void transactionalOperation() {
		
		update();
		ýnsert();
	}
	
	
	void ýnsert() {
		MyContext context = new MyContext();
		context.update();
	}
	
	void update() {
		MyContext context = new MyContext();
		context.update();
		}
}

package youtubeSrp;

public class CustomerManager {
	
	void transactionalOperation() {
		
		update();
		�nsert();
	}
	
	
	void �nsert() {
		MyContext context = new MyContext();
		context.update();
	}
	
	void update() {
		MyContext context = new MyContext();
		context.update();
		}
}

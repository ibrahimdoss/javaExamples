package youtubeOcp;

public class CustomerManager implements CustomerDalService {

	CustomerDalService customerDalService;
	
	
	
	
	
	public CustomerManager(CustomerDalService customerDalService) {
		super();
		this.customerDalService = customerDalService;
	}





	@Override
	public void add() {
		// TODO Auto-generated method stub
		
		customerDalService.add();
	}
	
	

}

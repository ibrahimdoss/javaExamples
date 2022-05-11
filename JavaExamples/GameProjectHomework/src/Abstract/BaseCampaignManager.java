package Abstract;

import Entities.Customer;
import Entities.Games;

public class BaseCampaignManager implements CampaignService {

	@Override
	public void campaignAdd(Games games, Customer customer) {
		System.out.println(games.getGameName()+" oyunu " +customer.getFirstName()+"taraf�ndan % "+games.getDiscount()+" indirime girdi ");
		
	}

	@Override
	public void campaignUpdate(Games games, Customer customer) {
		System.out.println(games.getGameName()+" oyununun yeni fiyat� "+games.getPrice());
		
	}

	@Override
	public void campaignDelete(Games games, Customer customer) {
		 System.out.println(games.getGameName()+" oyununun indirimine"+customer.getFirstName()+ " ki�isi taraf�ndan son verildi ");
	}

}

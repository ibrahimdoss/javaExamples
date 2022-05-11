package cleanCodeWork;

public class Customer {
	
	private int id;
	private String ad;
	
	public Customer() {
		super();
	}

	public Customer(int id, String ad) {
		super();
		this.id = id;
		this.ad = ad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}
}

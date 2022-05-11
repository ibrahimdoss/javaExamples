package cleanCodeWork;

public class TuzelMusteri extends Customer {

	private String taxNumber;
	private String title;
	
	public TuzelMusteri() {
		super();
	}

	
	public TuzelMusteri(String taxNumber, String title) {
		super();
		this.taxNumber = taxNumber;
		this.title = title;
	}


	public String getTaxNumber() {
		return taxNumber;
	}


	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	
}

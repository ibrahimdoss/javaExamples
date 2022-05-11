	package com.etiya.recap.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etiya.recap.business.abstracts.CorporateInvoicesService;
import com.etiya.recap.business.abstracts.CustomerFindeksScoreCheckService;
import com.etiya.recap.business.abstracts.IndividualInvoicesService;
import com.etiya.recap.business.abstracts.RentalService;
import com.etiya.recap.business.constants.Messages;
import com.etiya.recap.core.services.posService.PosService;
import com.etiya.recap.core.utilities.business.BusinessRules;
import com.etiya.recap.core.utilities.results.DataResult;
import com.etiya.recap.core.utilities.results.ErrorResult;
import com.etiya.recap.core.utilities.results.Result;
import com.etiya.recap.core.utilities.results.SuccessDataResult;
import com.etiya.recap.core.utilities.results.SuccessResult;
import com.etiya.recap.dataAccess.abstracts.AdditionalServicesDao;
import com.etiya.recap.dataAccess.abstracts.CarDao;
import com.etiya.recap.dataAccess.abstracts.CreditCardDao;
import com.etiya.recap.dataAccess.abstracts.RentalDao;
import com.etiya.recap.entities.concretes.AdditionalServices;
import com.etiya.recap.entities.concretes.ApplicationUser;
import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.CorporateCustomer;
import com.etiya.recap.entities.concretes.CreditCard;
import com.etiya.recap.entities.concretes.IndividualCustomer;
import com.etiya.recap.entities.concretes.Rental;
import com.etiya.recap.entities.requests.create.CreateDeliverTheCar;
import com.etiya.recap.entities.requests.create.CreateInvoicesRequest;
import com.etiya.recap.entities.requests.create.CreatePosServiceRequest;
import com.etiya.recap.entities.requests.create.CreateRentalRequest;
import com.etiya.recap.entities.requests.delete.DeleteRentalRequest;
import com.etiya.recap.entities.requests.update.UpdateRentalRequest;

@Service
public class RentalManager implements RentalService {

	private RentalDao rentalDao;
	private CarDao carDao;
	private CustomerFindeksScoreCheckService customerFindeksScoreCheckService;
	private CreditCardDao creditCardDao;
	private CorporateInvoicesService corporateInvoicesService;
	private IndividualInvoicesService individualInvoicesService;
	private PosService posService;
	private AdditionalServicesDao additionalServicesDao;

	@Autowired
	public RentalManager(RentalDao rentalDao, CarDao carDao,
			CustomerFindeksScoreCheckService customerFindeksScoreCheckService,CreditCardDao creditCardDao,
			CorporateInvoicesService corporateInvoicesService,IndividualInvoicesService individualInvoicesService
			,PosService posService,AdditionalServicesDao additionalServicesDao) {
		this.rentalDao = rentalDao;
		this.carDao = carDao;
		this.customerFindeksScoreCheckService = customerFindeksScoreCheckService;
		this.creditCardDao = creditCardDao;
		this.corporateInvoicesService = corporateInvoicesService;
		this.individualInvoicesService = individualInvoicesService;
		this.posService = posService;
		this.additionalServicesDao = additionalServicesDao;
	}

	@Override
	public DataResult<List<Rental>> getAll() {
		return new SuccessDataResult<List<Rental>>(this.rentalDao.findAll(), Messages.GetAll);
	}

	@Override
	public Result rentCorporateCustomer(CreateRentalRequest createRentalRequest) {
		
		Car car =this.carDao.getById(createRentalRequest.getCarId());
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createRentalRequest.getUserId());
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setApplicationUser(applicationUser);

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(createRentalRequest.getReturnCity());
		rental.setKilometer(createRentalRequest.getKilometer());
		
		CreditCard creditCard = new CreditCard();
		creditCard = new CreditCard();
		creditCard.setCardNumber(createRentalRequest.getCreditCardDto().getCardNumber());
		creditCard.setApplicationUser(applicationUser);
		creditCard.setCvc(createRentalRequest.getCreditCardDto().getCvc());
		creditCard.setExpirationDate(createRentalRequest.getCreditCardDto().getExpirationDate());
		creditCard.setNameOnTheCard(createRentalRequest.getCreditCardDto().getNameOnTheCard());
		
		
		//Araç bakımda ise kiralanamaz
		if(car.isCarIsAvailable()==false) {
			return new ErrorResult(Messages.ErrorIfCarIsNotAvailableToRent);
		}
		
		//Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rental.setRentalStartingCity(car.getCity());
		
		//Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(createRentalRequest.getReturnCity());
		
		//Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(createRentalRequest.getKilometer()+car.getKilometer());
		
		//Eğer müşteri kredi kartını kaydetmek istiyorsa kaydetme işlemi
		if(createRentalRequest.isSaveCreditCard()==true) {
			this.creditCardDao.save(creditCard);
		}
		
		//createRentalRequest içerisindeki additionalServiceIDlerini rental içindeki additionalServislere atma işlemi
		List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
		for (Integer additionalServiceId : createRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************
		
		//Kiralama gün sayısı ve total fiyat değişkenleri
		long totalRentDateCount=0;
		if(rental.getReturnDate()!=null) {
		totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
		}
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
		
		//Ek Hizmetleri Hesaplama Metodu
		rentPrice = this.priceCalculate(rental, rentPrice);
		
		//İş motoru
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkCorporateCustomerFindeksScore(corporateCustomer, car)
				,checkCreditCardNumber(creditCard));
		if (result != null) {
			return result;
		}

		//Kredi Kartı Limit Kontrolü********************************************************
		CreatePosServiceRequest createPosServiceRequest = new CreatePosServiceRequest();
		createPosServiceRequest.setCardNumber(creditCard.getCardNumber());
		createPosServiceRequest.setCvc(creditCard.getCvc());
		createPosServiceRequest.setExpirationDate(creditCard.getExpirationDate());
		createPosServiceRequest.setNameOnTheCard(creditCard.getNameOnTheCard());
		createPosServiceRequest.setFeePayable(rentPrice);
		
		//Eğer limit yetersiz ise rent işlemi gerçekleşmeyecek kontrolü
		if(this.posService.withdraw(createPosServiceRequest) == true)
		{
			this.rentalDao.save(rental);
		}
		else {
			return new ErrorResult(Messages.ErrorMoneyIsNotEnoughToRentACar);
		}
		//************************************************************************************
		
		
		//Eğer arabanın teslim günü belli ise fatura anında kesilecek
		if(rental.getReturnDate()!=null)
		{
	
		//Bunu save'den sonra yapmamızın sebebi,rental kayıt olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);
		
		}
		
		return new SuccessResult(true, Messages.Add);
	}

	@Override
	public Result rentIndividualCustomer(CreateRentalRequest createRentalRequest) {

		Car car =this.carDao.getById(createRentalRequest.getCarId());
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(createRentalRequest.getUserId());
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setApplicationUser(applicationUser);

		Rental rental = new Rental();
		rental.setRentDate(createRentalRequest.getRentDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(createRentalRequest.getReturnCity());
		rental.setKilometer(createRentalRequest.getKilometer());
		
		CreditCard creditCard = new CreditCard();
		creditCard = new CreditCard();
		creditCard.setCardNumber(createRentalRequest.getCreditCardDto().getCardNumber());
		creditCard.setApplicationUser(applicationUser);
		creditCard.setCvc(createRentalRequest.getCreditCardDto().getCvc());
		creditCard.setExpirationDate(createRentalRequest.getCreditCardDto().getExpirationDate());
		creditCard.setNameOnTheCard(createRentalRequest.getCreditCardDto().getNameOnTheCard());
		
		
		//Kiralama gün sayısı ve total fiyat değişkenleri
		long totalRentDateCount=0;
		if(rental.getReturnDate()!=null) {
			totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
		}
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
		
		
		
		//Araç bakımda ise kiralanamaz
		if(car.isCarIsAvailable()==false) {
			return new ErrorResult(Messages.ErrorIfCarIsNotAvailableToRent);
		}
		
		//Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rental.setRentalStartingCity(car.getCity());
		
		//Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(createRentalRequest.getReturnCity());
		
		//Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(createRentalRequest.getKilometer()+car.getKilometer());
		
		//Eğer müşteri kredi kartını kaydetmek istiyorsa kaydetme işlemi
		if(createRentalRequest.isSaveCreditCard()==true) {
			this.creditCardDao.save(creditCard);
		}
		//****************************************************************
		
		//createRentalRequest içerisindeki additionalServiceIDlerini rental içindeki additionalServislere atma işlemi
		List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
		for (Integer additionalServiceId : createRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************
		
		//Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
		if(rental.getReturnCity().equals(rental.getRentalStartingCity())==false)
		{
			rentPrice+=500;
		}
		
		//Hizmet bedelleri***********************************************************
		for (AdditionalServices additionalService : rental.getAdditionalServices()) {
			if(additionalService.getAdditionalServiceName().equals("BebekKoltugu")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Scooter")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Sigorta")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Kasko")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
		}
		
		
		
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkIndividualCustomerFindeksScore(individualCustomer, car)
				,checkCreditCardNumber(creditCard));
		if (result != null) {
			return result;
		}
		
		
		
		
		//Kredi Kartı Limit Kontrolü********************************************************
		CreatePosServiceRequest createPosServiceRequest = new CreatePosServiceRequest();
		createPosServiceRequest.setCardNumber(creditCard.getCardNumber());
		createPosServiceRequest.setCvc(creditCard.getCvc());
		createPosServiceRequest.setExpirationDate(creditCard.getExpirationDate());
		createPosServiceRequest.setNameOnTheCard(creditCard.getNameOnTheCard());
		createPosServiceRequest.setFeePayable(rentPrice);
		
		//Eğer limit yetersiz ise rent işlemi gerçekleşmeyecek kontrolü
		if(this.posService.withdraw(createPosServiceRequest) == true)
		{
			this.rentalDao.save(rental);
		}
		else {
			return new ErrorResult(Messages.ErrorMoneyIsNotEnoughToRentACar);
		}
		//************************************************************************************
		
		
		//Eğer arabanın teslim günü belli ise fatura anında kesilecek
		if(rental.getReturnDate()!=null)
		{
	
		//Bunu save'den sonra yapmamızın sebebi,rental kayıt olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);
		
		}
		
		return new SuccessResult(true, Messages.Add);

	}

	@Override
	public DataResult<Rental> getById(int id) {
		return new SuccessDataResult<Rental>(this.rentalDao.getById(id), Messages.GetById);
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());

		this.rentalDao.delete(rental);
		return new SuccessResult(true, Messages.Delete);
	}

	@Override
	public Result updateIndividualCustomerRent(UpdateRentalRequest updateRentalRequest) {

		Car car =this.carDao.getById(updateRentalRequest.getCarId());
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(updateRentalRequest.getUserId());
		
		IndividualCustomer individualCustomer = new IndividualCustomer();
		individualCustomer.setApplicationUser(applicationUser);

		Rental rental = this.rentalDao.getById(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(updateRentalRequest.getReturnCity());
		rental.setKilometer(updateRentalRequest.getKilometer());
		
		
		//Kiralama gün sayısı ve total fiyat değişkenleri
		long totalRentDateCount=0;
		if(rental.getReturnDate()!=null) {
		totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
		}
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
		
		
		//Araç bakımda ise kiralanamaz
		if(car.isCarIsAvailable()==false) {
			return new ErrorResult(Messages.ErrorIfCarIsNotAvailableToRent);
		}
		
		//Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rental.setRentalStartingCity(car.getCity());
		
		//Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(updateRentalRequest.getReturnCity());
		
		//Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(updateRentalRequest.getKilometer()+car.getKilometer());
		
		//Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
		if(rental.getReturnCity().equals(rental.getRentalStartingCity())==false)
		{
			rentPrice+=500;
		}
		
		//createRentalRequest içerisindeki additionalServiceIDlerini rental içindeki additionalServislere atma işlemi
		List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
		for (Integer additionalServiceId : updateRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************
		
		
		//Hizmet bedelleri***********************************************************
		for (AdditionalServices additionalService : rental.getAdditionalServices()) {
			if(additionalService.getAdditionalServiceName().equals("BebekKoltugu")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Scooter")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Sigorta")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Kasko")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
		}
		
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkIndividualCustomerFindeksScore(individualCustomer, car));
		if (result != null) {
			return result;
		}
		
		//Eğer arabanın teslim günü belli ise fatura anında kesilecek
		if(rental.getReturnDate()!=null)
		{
	
		//Bunu save'den sonra yapmamızın sebebi,rental kayıt olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);
		
		}
		
		return new SuccessResult(true, Messages.Add);
	}
	
	@Override
	public Result updateCorporateCustomerRent(UpdateRentalRequest updateRentalRequest) {

		Car car =this.carDao.getById(updateRentalRequest.getCarId());
		
		ApplicationUser applicationUser = new ApplicationUser();
		applicationUser.setUserId(updateRentalRequest.getUserId());
		
		CorporateCustomer corporateCustomer = new CorporateCustomer();
		corporateCustomer.setApplicationUser(applicationUser);

		Rental rental = this.rentalDao.getById(updateRentalRequest.getId());
		rental.setRentDate(updateRentalRequest.getRentDate());
		rental.setReturnDate(updateRentalRequest.getReturnDate());
		rental.setCar(car);
		rental.setUser(applicationUser);
		rental.setReturnCity(updateRentalRequest.getReturnCity());
		rental.setKilometer(updateRentalRequest.getKilometer());
		
		
		//Kiralama gün sayısı ve total fiyat değişkenleri
		long totalRentDateCount=0;
		if(rental.getReturnDate()!=null) {
		totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
		}
		double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
		
		
		//Araç bakımda ise kiralanamaz
		if(car.isCarIsAvailable()==false) {
			return new ErrorResult(Messages.ErrorIfCarIsNotAvailableToRent);
		}
		
		//Arabanın kiralanmadan önceki ili kiralanma şehri olacak
		rental.setRentalStartingCity(car.getCity());
		
		//Arabayı tekrar teslim ettiğimizde teslim ili arabanın bulunduğu il olacak
		car.setCity(updateRentalRequest.getReturnCity());
		
		//Müşterinin girdiği km bilgisini arabanın km bilgisi ile güncelleme
		car.setKilometer(updateRentalRequest.getKilometer()+car.getKilometer());
		
		//Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
		if(rental.getReturnCity().equals(rental.getRentalStartingCity())==false)
		{
			rentPrice+=500;
		}
		
		
		//createRentalRequest içerisindeki additionalServiceIDlerini rental içindeki additionalServislere atma işlemi
		List<AdditionalServices> additionalServices = new ArrayList<AdditionalServices>();
		for (Integer additionalServiceId : updateRentalRequest.getAdditionalServicesId()) {
			additionalServices.add(this.additionalServicesDao.getById(additionalServiceId));
		}
		rental.setAdditionalServices(additionalServices);
		//*************************************************************************************************************
		
		
		//Hizmet bedelleri***********************************************************
		for (AdditionalServices additionalService : rental.getAdditionalServices()) {
			if(additionalService.getAdditionalServiceName().equals("BebekKoltugu")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Scooter")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Sigorta")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
			if(additionalService.getAdditionalServiceName().equals("Kasko")) {
				rentPrice+=additionalService.getAdditionalServicePrice();
			}
		}
		
		var result = BusinessRules.run(checkCarIsReturned(car.getId()),checkCorporateCustomerFindeksScore(corporateCustomer, car));
		if (result != null) {
			return result;
		}
		
		//Eğer arabanın teslim günü belli ise fatura anında kesilecek
		if(rental.getReturnDate()!=null)
		{
	
		//Bunu save'den sonra yapmamızın sebebi,rental kayıt olduktan sonra IDsini get edebiliyoruz.
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);
		
		}
		
		return new SuccessResult(true, Messages.Add);
	}

	private Result checkCarIsReturned(int carId) {
		Rental rental = this.rentalDao.getByCar_idAndReturnDateIsNull(carId);
		if(rental!=null) {
			return new ErrorResult(Messages.ErrorIfCarIsNotAvailable);
		}else {
			return new SuccessResult();
		}
	}
	
	private Result checkIndividualCustomerFindeksScore(IndividualCustomer individualCustomer,Car car) {
		int individualCustomerFindeksScore = this.customerFindeksScoreCheckService.checkIndividualCustomerFindeksScore(individualCustomer);
		if(car.getFindeksScore()>individualCustomerFindeksScore) {
			return new ErrorResult(Messages.ErrorFindeksScore);
		}
		return new SuccessResult();
	}
	
	private Result checkCorporateCustomerFindeksScore(CorporateCustomer corporateCustomer, Car car) {
		int corporateCustomerFindeksScore = this.customerFindeksScoreCheckService.checkCorporateCustomerFindeksScore(corporateCustomer);
		if(car.getFindeksScore()>corporateCustomerFindeksScore) {
			return new ErrorResult(Messages.ErrorFindeksScore);
		}
		return new SuccessResult();
	}
	
	//Kredi Kart Numarası Kontrolü
	private Result checkCreditCardNumber(CreditCard creditCard) {
		
		String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
		        "(?<mastercard>5[1-5][0-9]{14})|" +
		        "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
		        "(?<amex>3[47][0-9]{13})|" +
		        "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
		        "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
		
		Pattern pattern = Pattern.compile(regex);
		creditCard.setCardNumber(creditCard.getCardNumber().replaceAll("-", ""));
		Matcher matcher = pattern.matcher(creditCard.getCardNumber());
		if(matcher.matches()==true) {
			return new SuccessResult();
		}else {
			return new ErrorResult(Messages.ErrorIfCreditCardIsWrong);
			}
		}

	@Override
	public Result deliverCorporateCustomerCar(CreateDeliverTheCar createDeliverTheCar) {
		
		Rental rental=this.rentalDao.getById(createDeliverTheCar.getRentalId());
		
		rental.setReturnDate(createDeliverTheCar.getReturnDate());
		
		  
	   	long totalRentDateCount=0;
	   	if(rental.getReturnDate()!=null) {
	   	 totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
	   	 }
	   	double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
	   	
	  
	  //Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
	  if(rental.getReturnCity().equals(rental.getRentalStartingCity())==false)
	  {
	  	rentPrice+=500;
	  }
	  		
	  //createRentalRequest içerisindeki additionalServiceIDlerini rental içindeki additionalServislere atma işlemi
	  List<AdditionalServices> additionalServices = rental.getAdditionalServices();
	  		
	  //Hizmet bedelleri***********************************************************
	  for (AdditionalServices additionalService : additionalServices) {
	  	if(additionalService.getAdditionalServiceName().equals("BebekKoltugu")) {
	  		rentPrice+=additionalService.getAdditionalServicePrice();
	  	}
	  	if(additionalService.getAdditionalServiceName().equals("Scooter")) {
	  		rentPrice+=additionalService.getAdditionalServicePrice();
	  	}
	  	if(additionalService.getAdditionalServiceName().equals("Sigorta")) {
	  		rentPrice+=additionalService.getAdditionalServicePrice();
	  	}
	  	if(additionalService.getAdditionalServiceName().equals("Kasko")) {
	  		rentPrice+=additionalService.getAdditionalServicePrice();
	  	}
	  }
		
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.corporateInvoicesService.add(createInvoicesRequest);
		
		return new SuccessResult(true,Messages.GetAll);
		
	}

	@Override
	public Result deliverIndividualCustomerCar(CreateDeliverTheCar createDeliverTheCar) {
		
       Rental rental=this.rentalDao.getById(createDeliverTheCar.getRentalId());
   	   rental.setReturnDate(createDeliverTheCar.getReturnDate());
   	   
   	   long totalRentDateCount=0;
   	   if(rental.getReturnDate()!=null) {
   		  totalRentDateCount = ChronoUnit.DAYS.between(rental.getRentDate().toInstant(),rental.getReturnDate().toInstant());
   	   }
   	   double rentPrice = (rental.getCar().getDailyPrice() * totalRentDateCount);
		
		Date dateNow = new java.sql.Date(new java.util.Date().getTime());
		CreateInvoicesRequest createInvoicesRequest = new CreateInvoicesRequest();
		createInvoicesRequest.setCreationDate(dateNow);
		createInvoicesRequest.setRentalId(rental.getId());
		createInvoicesRequest.setRentPrice(rentPrice);
		createInvoicesRequest.setTotalRentDateCount(totalRentDateCount);
		this.individualInvoicesService.add(createInvoicesRequest);
		
		return new SuccessResult(true,Messages.GetAll);
	}

	//Hizmet bedelleri hesaplama************************************************
	private double priceCalculate(Rental rental,double rentPrice) {
		//Araç başlangıç şehrine teslim edilmediyse 500 tl fark alınacak
		if(rental.getReturnCity().equals(rental.getRentalStartingCity())==false)
			{
			rentPrice+=500;
			}
		for (AdditionalServices additionalServicee : rental.getAdditionalServices()) {
			rentPrice+=additionalServicee.getAdditionalServicePrice();
			}
		return rentPrice;
		}
	

	//TODO Araba müşterideyse carAvailable is false olmalı
}

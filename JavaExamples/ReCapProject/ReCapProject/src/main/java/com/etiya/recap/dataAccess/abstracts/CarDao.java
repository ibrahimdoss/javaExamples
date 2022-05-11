package com.etiya.recap.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etiya.recap.entities.concretes.Car;
import com.etiya.recap.entities.concretes.CarImages;
import com.etiya.recap.entities.dtos.CarDetailDto;
import com.etiya.recap.entities.dtos.CarDetailWithCarImgDto;


@Repository
public interface CarDao extends JpaRepository<Car, Integer>{

	@Query("Select new com.etiya.recap.entities.dtos.CarDetailDto (c.id,b.brandName,cl.colorName,c.dailyPrice,c.city,c.kilometer) From Car c Inner Join c.brand b Inner Join c.color cl")
	List<CarDetailDto> getCarWithDetails();
	
	@Query("SELECT c.carImages FROM Car c WHERE c.id= :carId")
	List<CarImages> getCarImagesByCarId(int carId);
	
	List<Car> getByBrand_brandId(int brandId);
	
	List<Car> getByColor_colorId(int colorId);
	
	@Query("Select c.findeksScore FROM Car c Where c.id=:carId")
	int getFindeksScoreByCarId(int carId);
	
	@Query("Select new com.etiya.recap.entities.dtos.CarDetailWithCarImgDto ( c.id,b.brandName,cl.colorName,c.dailyPrice,c.city,c.kilometer,ci.imagePath) "
			+ "From Car c Inner Join c.brand b Inner Join c.color cl Inner Join c.carImages ci Where c.id=:id ")
	List<CarDetailWithCarImgDto> getCarWithCarImg(int id);
	
	@Query("FROM Car c Where c.city=:city")
	List<Car> getCars_ByCity(String city);
	
	@Query("FROM Car c Where c.carIsAvailable=true")
	List<Car> getCarsAreAvailable();
	
	@Query("FROM Car c Where c.carIsAvailable=false")
	List<Car> getCarsAreNotAvailable();
	
	@Query("FROM Car c WHERE c.carIsAvailable=false AND c.id=:carId")
	boolean getCarReturnDateIsNull(int carId); 
	
}
	



package sg.edu.iss.kuruma.service;

import java.util.List;

import sg.edu.iss.kuruma.model.Car;

public interface CarService {
	public void saveCar(Car car);    
    public void deleteCar(Car car);
    public List<Car> listCar();
    public List<Car> findByBrand(String brand);
    public List<Car> findByModel(String model);
    public Car findById(Integer id);
    public List<Car> findSearchByEntry(String entry);
    public List<Car> findAllCars();
    public List<Car> sortSearchByPrice(String entry);
    public List<Car> androidList(List<Car> cars);
}
package pl.pjatk.CarRental_v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findByID(Long id) {

        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Car car) {
        return carRepository.save(car);
    }

    public boolean deleteCar(Long id) {
        if (carRepository.findById(id).isPresent()) {
            if (carRepository.findById(id).get().getTransactionsList().isEmpty()) {
                carRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

}

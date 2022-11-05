package pl.pjatk.CarRental_v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.repository.CarRepository;
import pl.pjatk.CarRental_v2.repository.CustomerRepository;

@Component
public class DataLoader implements ApplicationRunner {
    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public DataLoader(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public void run(ApplicationArguments args) {
        carRepository.save(new Car("Ford", "Panda", "red", 2000, 200));
        carRepository.save(new Car("BMW", "e36", "black", 1991, 50));
        carRepository.save(new Car("Audi", "A6", "silver", 2010, 1000));
        carRepository.save(new Car("Mazda", "RX7", "silver", 2012, 1000));
        carRepository.save(new Car("VW", "golf 3", "blue", 1995, 100));
        customerRepository.save(new Customer("Daniel", 600));
        customerRepository.save(new Customer("Marek", 300));
        customerRepository.save(new Customer("Karol", 2000));
        customerRepository.save(new Customer("Mario", 4000));
        customerRepository.save(new Customer("Magdalena", 1500));
    }

}

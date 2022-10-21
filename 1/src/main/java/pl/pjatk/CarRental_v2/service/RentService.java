package pl.pjatk.CarRental_v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.model.Rent;
import pl.pjatk.CarRental_v2.repository.RentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {

    private RentRepository rentRepository;
    private CarService carService;
    private CustomerService customerService;

    @Autowired
    public RentService(RentRepository rentRepository, CarService carService, CustomerService customerService) {
        this.rentRepository = rentRepository;
        this.carService = carService;
        this.customerService = customerService;
    }

    public List<Rent> findAll() {
        return rentRepository.findAll();
    }

    public List<Rent> findAllCurrent() {
        return rentRepository.findAllByEndDateIsNull().get();
    }

    public Optional<Rent> findById(Long id) {
        return rentRepository.findById(id);
    }


    public List<Rent> findAllByCustomer(Long customerId) {
        return rentRepository.findAllByCustomer(customerService.findByID(customerId).get()).get();
    }

    public List<Rent> findAllCurrentByCustomer(Long customerId) {
        return rentRepository.findAllByCustomerAndEndDateIsNull(customerService.findByID(customerId).get()).get();
    }

    public List<Rent> findAllByCar(Long carId) {
        return rentRepository.findAllByCar(carService.findByID(carId).get()).get();
    }

    public Optional<Rent> findCurrentByCar(Long catId) {
        return rentRepository.findByCarAndEndDateIsNull(carService.findByID(catId).get());
    }

    public Rent rentCar(Customer customer, Car car, int days) {
        int price = toPay(car, days);
        customer.setWallet(customer.getWallet() - price);
        car.setAvailable(false);
        carService.updateCar(car);
        customerService.updateCustomer(customer);
        return rentRepository.save(new Rent(price, customer, car));
    }


    public Rent newReturn(Rent rent, String comment, int delayDays, int otherPayments) {
        Car car = rent.getCar();
        if (delayDays > 0 || otherPayments > 0) {
            writeDifficulties(rent, car, delayDays, otherPayments);
        } else {
            rent.setDifficulties("Zwrot samochodu w terminie.");
        }

        rent.setEndDate(LocalDate.now());
        rent.setComment(comment);
        car.setAvailable(true);
        carService.updateCar(car);

        return rentRepository.save(rent);

    }


    public boolean canRent(Optional<Customer> customer, Optional<Car> car, int days) {
        if (customer.isPresent() && car.isPresent()) {
            if (customer.get().getWallet() > toPay(car.get(), days) && car.get().isAvailable()) {
                return true;
            }
        }
        return false;
    }


    public void writeDifficulties(Rent rent, Car car, int delayDays, int otherPayments) {
        Customer customer = rent.getCustomer();
        int additionalPayments = (toPay(car, delayDays) * 2) + otherPayments;
        if (delayDays > 0) {
            rent.setDifficulties("Klient spóźnił sie " + delayDays + " dni. ");
        }
        if (otherPayments > 0) {
            if (rent.getDifficulties() == null) {
                rent.setDifficulties("Klient musiał zapłacić dodatkowo za uszkodzenia, lub inne zobowiązania zawarte w regulaminie.");
            } else {
                rent.setDifficulties(rent.getDifficulties() + "Klient musiał zapłacić dodatkowo za uszkodzenia, lub inne zobowiązania zawarte w regulaminie.");
            }
        }
        rent.setDifficulties(rent.getDifficulties() + "Łączna suma dodatkowych płatności: " + additionalPayments + "zł ");

        if (customer.getWallet() >= additionalPayments) {
            customer.setWallet(customer.getWallet() - additionalPayments);
        } else {
            int inCash = additionalPayments - customer.getWallet();
            rent.setDifficulties(rent.getDifficulties() + "Klient zapłacił " + inCash + "zł w gotówce.");
            customer.setWallet(0);
        }
        rent.setTotalPrice(rent.getTotalPrice() + additionalPayments);
        customerService.updateCustomer(customer);
    }


    public int toPay(Car car, int days) {
        int toPay = car.getPricePerDay() * days;
        return toPay;
    }

    public void deleteAll() {
        rentRepository.deleteAll();
    }

}

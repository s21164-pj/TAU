package pl.pjatk.CarRental_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.model.Rent;
import pl.pjatk.CarRental_v2.service.CarService;
import pl.pjatk.CarRental_v2.service.CustomerService;
import pl.pjatk.CarRental_v2.service.RentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rent")
public class RentController {

    private RentService rentService;
    private CustomerService customerService;
    private CarService carService;

    @Autowired
    public RentController(RentService rentService, CustomerService customerService, CarService carService) {
        this.rentService = rentService;
        this.customerService = customerService;
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rent>> findAll() {
        return ResponseEntity.ok(rentService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Rent>> findById(@RequestParam Long id) {
        return ResponseEntity.ok(rentService.findById(id));
    }

    @GetMapping("/allCurrent")
    public ResponseEntity<List<Rent>> findAllCurrent() {
        return ResponseEntity.ok(rentService.findAllCurrent());
    }

    @GetMapping("/byCustomer")
    public ResponseEntity<List<Rent>> findAllByCustomer(Long id) {
        return ResponseEntity.ok(rentService.findAllByCustomer(id));
    }

    @GetMapping("/currentByCustomer")
    public ResponseEntity<List<Rent>> findAllCurrentByCustomer(Long id) {
        return ResponseEntity.ok(rentService.findAllCurrentByCustomer(id));
    }

    @GetMapping("/byCar")
    public ResponseEntity<List<Rent>> findAllByCar(Long id) {
        return ResponseEntity.ok(rentService.findAllByCar(id));
    }

    @PostMapping("/rentCar")
    public ResponseEntity<Rent> rentCar(@RequestParam Long customerId, @RequestParam Long carId, @RequestParam int days) {
        Optional<Customer> customer = customerService.findByID(customerId);
        Optional<Car> car = carService.findByID(carId);
        if (rentService.canRent(customer, car, days)) {
            return ResponseEntity.ok(rentService.rentCar(customer.get(), car.get(), days));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/returnByCar")
    public ResponseEntity<Rent> returnCarByCarId(@RequestParam Long carId, @RequestParam String comment, @RequestParam int delayDays, @RequestParam int otherPayments) {
        Optional<Rent> rent = rentService.findCurrentByCar(carId);
        if (rent.isPresent()) {
            return ResponseEntity.ok(rentService.newReturn(rent.get(), comment, delayDays, otherPayments));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/returnByRent")
    public ResponseEntity<Rent> returnCarByRentId(@RequestParam Long rentId, @RequestParam String comment, @RequestParam int delayDays, @RequestParam int otherPayments) {
        Optional<Rent> rent = rentService.findById(rentId);
        if (rent.isPresent() && rent.get().getEndDate() == null) {
            return ResponseEntity.ok(rentService.newReturn(rent.get(), comment, delayDays, otherPayments));
        }
        return ResponseEntity.notFound().build();
    }

}

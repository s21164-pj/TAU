package pl.pjatk.CarRental_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Car> findByID(@RequestParam Long id) {
        Optional<Car> byId = carService.findByID(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        return ResponseEntity.ok(carService.addCar(car));
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(Car car) {
        return ResponseEntity.ok(carService.updateCar(car));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCar(@RequestParam long id) {
        carService.deleteCar(id);
        return ResponseEntity.ok().build();
    }


}

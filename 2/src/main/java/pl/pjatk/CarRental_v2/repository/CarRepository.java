package pl.pjatk.CarRental_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pjatk.CarRental_v2.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}

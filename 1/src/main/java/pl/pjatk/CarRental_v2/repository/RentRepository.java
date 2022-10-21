package pl.pjatk.CarRental_v2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pjatk.CarRental_v2.model.Car;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.model.Rent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {


    Optional<Rent> findByCarAndEndDateIsNull(Car car);

    Optional<List<Rent>> findAllByCustomer(Customer customer);

    Optional<List<Rent>> findAllByCar(Car car);

    Optional<List<Rent>> findAllByCarAndEndDateIsNull(Car car);

    Optional<List<Rent>> findAllByCustomerAndEndDateIsNull(Customer customer);

    Optional<List<Rent>> findAllByEndDateIsNull();

    Optional<List<Rent>> findAllByStartingDateBetween(LocalDate start, LocalDate end);


}

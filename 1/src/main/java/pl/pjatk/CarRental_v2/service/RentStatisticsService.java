package pl.pjatk.CarRental_v2.service;

import org.springframework.stereotype.Service;
import pl.pjatk.CarRental_v2.model.Rent;
import pl.pjatk.CarRental_v2.repository.RentRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class RentStatisticsService {
    private RentRepository rentRepository;
    private CarService carService;
    private CustomerService customerService;

    public RentStatisticsService(RentRepository rentRepository, CarService carService, CustomerService customerService) {
        this.rentRepository = rentRepository;
        this.carService = carService;
        this.customerService = customerService;
    }


    public String totalProfit() {
        return profit(rentRepository.findAll());
    }

    public String last30DaysProfit() {
        LocalDate startDate = LocalDate.now().minusDays(30);

        return profit(rentRepository.findAllByStartingDateBetween(startDate, LocalDate.now()).get());
    }

    public String currentMonthProfit() {
        Month currentMonth = LocalDate.now().getMonth();
        int currentYear = LocalDate.now().getYear();
        List<Rent> currentMonthRents = rentRepository.findAllByStartingDateBetween(LocalDate.of(currentYear, currentMonth, 01), LocalDate.now()).get();
        return profit(currentMonthRents);
    }

    public String currentYearProfit() {
        int currentYear = LocalDate.now().getYear();
        return profit(rentRepository.findAllByStartingDateBetween(LocalDate.of(currentYear, 01, 01), LocalDate.now()).get());
    }


    public String profit(List<Rent> rentList) {
        int total = 0;

        for (Rent rent : rentList) {
            total += rent.getTotalPrice();
        }
        return "Całkowity zysk: " + total + "zł. Liczba wypożyczeń: " + rentList.size() + "." + " Średnio " + total / rentList.size() + "zł na transakcje.";
    }

}

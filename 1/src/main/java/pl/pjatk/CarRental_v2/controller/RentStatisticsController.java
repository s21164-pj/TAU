package pl.pjatk.CarRental_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pjatk.CarRental_v2.service.RentStatisticsService;

@RestController
@RequestMapping("/statistic")
public class RentStatisticsController {

    private RentStatisticsService rentStatisticsService;

    @Autowired
    public RentStatisticsController(RentStatisticsService rentStatisticsService) {
        this.rentStatisticsService = rentStatisticsService;
    }


    @GetMapping("/total")
    public ResponseEntity<String> totalProfit() {
        return ResponseEntity.ok(rentStatisticsService.totalProfit());
    }

    @GetMapping("/last30Days")
    public ResponseEntity<String> last30DaysProfit() {
        return ResponseEntity.ok(rentStatisticsService.last30DaysProfit());
    }

    @GetMapping("/currentMonth")
    public ResponseEntity<String> currentMonthProfit() {
        return ResponseEntity.ok(rentStatisticsService.currentMonthProfit());
    }

    @GetMapping("/currentYear")
    public ResponseEntity<String> currentYearProfit() {
        return ResponseEntity.ok(rentStatisticsService.currentYearProfit());
    }
}

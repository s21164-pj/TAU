package pl.pjatk.CarRental_v2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.service.CustomerService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/id")
    public ResponseEntity<Customer> findByID(@RequestParam Long id) {
        Optional<Customer> byId = customerService.findByID(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCustomer(@RequestParam long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(Customer customer) {
        return ResponseEntity.ok(customerService.updateCustomer(customer));
    }

    @PutMapping("/deposit")
    public ResponseEntity<Customer> depositMoney(@RequestParam Long id, @RequestParam int deposit) {
        Optional<Customer> customer = customerService.findByID(id);
        return ResponseEntity.ok(customerService.depositMoney(customer.get(), deposit));
    }
}

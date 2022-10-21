package pl.pjatk.CarRental_v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pjatk.CarRental_v2.model.Customer;
import pl.pjatk.CarRental_v2.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findByID(Long id) {
        return customerRepository.findById(id);
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            if (customerRepository.findById(id).get().getTransactions().isEmpty()) {
                customerRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public Customer depositMoney(Customer customer, int deposit) {
        customer.setWallet(customer.getWallet() + deposit);
        return customerRepository.save(customer);
    }

    /*public Customer depositMoney(Long customerID, int deposit) {
        Customer customer = customerRepository.findById(customerID).get();
        customer.setWallet(customer.getWallet() + deposit);
        return customerRepository.save(customer);
    }*/

}

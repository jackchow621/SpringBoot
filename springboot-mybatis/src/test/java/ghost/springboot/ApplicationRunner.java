package ghost.springboot;

import ghost.springboot.dao.CustomerRepository;
import ghost.springboot.entity.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner{

	@Autowired
	private CustomerRepository repository;

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		repository.save(new Customer("Jack", "Chow"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		System.out.println("Customers found with findByLastName('Chow'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Chow")) {
			System.out.println(customer);
		}
	}
}

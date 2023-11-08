package graduate.repository;

import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	List<Customer> findByNameContaining(String name);
	
	Customer findByAccount_Username(String username);

	Page<Customer> findByNameContaining(String name, Pageable pageable);
}

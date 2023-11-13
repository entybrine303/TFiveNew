package graduate.repository;

import graduate.domain.Cart;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, String>{
	List<Cart> findByCustomer_CustomerID(String customerID);
	 @Transactional
	 void deleteByCustomer_CustomerID(String customerID);
}

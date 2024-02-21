package graduate.repository;

import graduate.domain.Cart;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CartRepository extends JpaRepository<Cart, String>{
	List<Cart> findByCustomer_CustomerID(String customerID);
	 @Transactional
	 void deleteByCustomer_CustomerID(String customerID);
	 
	 @Query("SELECT COUNT(c) FROM Cart c WHERE c.customer.customerID = :customerID")
	    Long countByCustomerID(@Param("customerID") String customerID);
	 
	 @Query("SELECT c FROM Cart c WHERE c.dish.dishID = :productId AND c.customer.customerID = :customerId")
	    Cart findByProductIdAndCustomerId(@Param("productId") String productId, @Param("customerId") String customerId);

}

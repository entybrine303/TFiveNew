package graduate.repository;

import graduate.domain.Wishlist;
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

public interface WishlistRepository extends JpaRepository<Wishlist, String>{
	List<Wishlist> findByCustomer_CustomerID(String customerID);
	 @Transactional
	 void deleteByCustomer_CustomerID(String customerID);
	 
	 @Transactional
	 void deleteByDish_DishID(String dishID);
	 
	 @Query("SELECT COUNT(c) FROM Wishlist c WHERE c.customer.customerID = :customerID")
	    Long countByCustomerID(@Param("customerID") String customerID);
}

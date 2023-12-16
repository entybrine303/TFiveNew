package graduate.repository;

import graduate.domain.Wishlist;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface WishlistRepository extends JpaRepository<Wishlist, String>{
	List<Wishlist> findByCustomer_CustomerID(String customerID);
	 
	 @Transactional
	 void deleteByDish_DishID(String dishID);
	 
	 @Query("SELECT COUNT(c) FROM Wishlist c WHERE c.customer.customerID = :customerID")
	    Long countByCustomerID(@Param("customerID") String customerID);
	 
	 @Query("SELECT w FROM Wishlist w WHERE w.dish.dishID = :productId AND w.customer.customerID = :customerId")
	    Optional<Wishlist> findByProductIdAndCustomerId(@Param("productId") String productId, @Param("customerId") String customerId);
	 @Modifying
	    @Query("DELETE FROM Wishlist w WHERE w.dish.dishID = :productId AND w.customer.customerID = :customerId")
	    void deleteByProductIdAndCustomerId(@Param("productId") String productId, @Param("customerId") String customerId);
}

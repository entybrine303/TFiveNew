package graduate.repository;

import graduate.domain.Order;
import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.Dish;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, String> {
	List<Order> findByCustomer_CustomerID(String customerID);
	
	List<Order> findByDriver_DriverID(String driverID);

	@Transactional
	void deleteByCustomer_CustomerID(String customerID);

	@Transactional
	@Modifying
	@Query("UPDATE Order SET status = :value WHERE orderID = :id")
	void updateStatusdById(@Param("value") String value, @Param("id") String id);

	@Transactional
	@Modifying
	@Query("UPDATE Order SET driverid = :value WHERE orderID = :id")
	void receivedOrder(@Param("value") String driverID, @Param("id") String orderID);
}

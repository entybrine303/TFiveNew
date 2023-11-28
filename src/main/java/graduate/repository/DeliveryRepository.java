package graduate.repository;

import graduate.domain.Delivery;
import graduate.domain.Order;
import graduate.domain.OrderDetail;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DeliveryRepository extends JpaRepository<Delivery, String> {
	@Transactional
	void deleteByOrders_OrderID(String orderID);
	
	Delivery findByOrders_OrderID(String orderID);


	@Transactional
	@Modifying
	@Query("UPDATE Delivery d SET "
			+ "d.ConfimedOrder = CASE WHEN :fieldName = 'Confimed' THEN CURRENT_TIMESTAMP ELSE d.ConfimedOrder END, "
			+ "d.FinishedOrder = CASE WHEN :fieldName = 'Finished' THEN CURRENT_TIMESTAMP ELSE d.FinishedOrder END, "
			+ "d.ReceivedOrder = CASE WHEN :fieldName = 'Received' THEN CURRENT_TIMESTAMP ELSE d.ReceivedOrder END, "
			+ "d.TookOrder = CASE WHEN :fieldName = 'Took' THEN CURRENT_TIMESTAMP ELSE d.TookOrder END, "
			+ "d.CanceledOrder = CASE WHEN :fieldName = 'Canceled' THEN CURRENT_TIMESTAMP ELSE d.CanceledOrder END, "
			+ "d.CompleteOrder = CASE WHEN :fieldName = 'Completed' THEN CURRENT_TIMESTAMP ELSE d.CompleteOrder END "
			+ "WHERE d.orders.orderID = :orderID")
	void updateSavedDateByOrderID(@Param("fieldName") String fieldName, @Param("orderID") String orderID);

	@Query("SELECT MAX(COALESCE(d.ConfimedOrder, d.FinishedOrder, d.ReceivedOrder, d.TookOrder, d.CanceledOrder, d.CompleteOrder)) "
			+ "FROM Delivery d WHERE d.orders.orderID = :orderID")
	Date findLatestUpdateDatetimeByOrderID(@Param("orderID") String orderID);
}

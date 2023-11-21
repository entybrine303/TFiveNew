package graduate.repository;

import graduate.domain.Delivery;
import graduate.domain.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

public interface DeliveryRepository extends JpaRepository<Delivery, String>{
	 @Transactional
	void deleteByOrders_OrderID(String orderID);
}

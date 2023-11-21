package graduate.repository;

import graduate.domain.OrderDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.transaction.annotation.Transactional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String>{
	List<OrderDetail> findByOrders_OrderID(String orderID);
	 @Transactional
	void deleteByOrders_OrderID(String orderID);
}

package graduate.service.imp;

import graduate.domain.DriverRegister;
import graduate.domain.Order;
import graduate.repository.CartRepository;
import graduate.repository.DeliveryRepository;
import graduate.repository.OrderRepository;
import graduate.service.CartService;
import graduate.service.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

@Repository
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public void updateStatus(String value, String id) {
		orderRepository.updateStatusdById(value, id);
		
		String fieldName = null;
		if (value.equals("Xác nhận")) fieldName="Confimed";
		else if (value.equals("Đã xong")) fieldName="Finished";
		else if (value.equals("Đã nhận")) fieldName="Received";
		else if (value.equals("Đã lấy")) fieldName="Took";
		else if (value.equals("Đã huỷ")) fieldName="Canceled";
		else if (value.equals("Hoàn thành")) fieldName="Completed";
		deliveryRepository.updateSavedDateByOrderID(fieldName, id);
	}
	
	@Override
	public void receivedOrder(String driverID, String orderID) {
		orderRepository.receivedOrder(driverID, orderID);
	}
	
	@Override
	public void deleteByCustomer_CustomerID(String customerID) {
		orderRepository.deleteByCustomer_CustomerID(customerID);
	}

	@Override
	public List<Order> findByCustomer_CustomerID(String customerID) {
		// TODO Auto-generated method stub
		return orderRepository.findByCustomer_CustomerID(customerID);
	}

	@Override
	public Order save(Order entity) {
		return orderRepository.save(entity);
	}

	@Override
	public <S extends Order> Optional<S> findOne(Example<S> example) {
		return orderRepository.findOne(example);
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Page<Order> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	@Override
	public List<Order> findAll(Sort sort) {
		return orderRepository.findAll(sort);
	}

	@Override
	public List<Order> findAllById(Iterable<String> ids) {
		return orderRepository.findAllById(ids);
	}

	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		return orderRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderRepository.flush();
	}

	@Override
	public Order saveAndFlush(Order entity) {
		return orderRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Order> findById(String id) {
		return orderRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Order> entities) {
		orderRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return orderRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<Order> entities) {
		orderRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Order> boolean exists(Example<S> example) {
		return orderRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		orderRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		orderRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		orderRepository.deleteById(id);
	}

	@Override
	public Order getOne(String id) {
		return orderRepository.getOne(id);
	}

	@Override
	public void delete(Order entity) {
		orderRepository.delete(entity);
	}

	@Override
	public Order getById(String id) {
		return orderRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		orderRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		orderRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderRepository.deleteAll();
	}

	@Override
	public Order getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		return orderRepository.findAll(example);
	}

	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		return orderRepository.findAll(example, sort);
	}


}

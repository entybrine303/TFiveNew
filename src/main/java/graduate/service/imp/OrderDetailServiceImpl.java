package graduate.service.imp;

import graduate.domain.DriverRegister;
import graduate.domain.OrderDetail;
import graduate.repository.CartRepository;
import graduate.repository.OrderDetailRepository;
import graduate.service.CartService;
import graduate.service.OrderDetailService;
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
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void deleteByOrder_OrderID(String orderID)
	{
		orderDetailRepository.deleteByOrders_OrderID(orderID);
	}

	@Override
	public List<OrderDetail> findByOrder_OrderID(String orderID)

	{
		// TODO Auto-generated method stub
		return orderDetailRepository.findByOrders_OrderID(orderID);
	}

	@Override
	public OrderDetail save(OrderDetail entity) {
		return orderDetailRepository.save(entity);
	}

	@Override
	public <S extends OrderDetail> Optional<S> findOne(Example<S> example) {
		return orderDetailRepository.findOne(example);
	}

	@Override
	public List<OrderDetail> findAll() {
		return orderDetailRepository.findAll();
	}

	@Override
	public Page<OrderDetail> findAll(Pageable pageable) {
		return orderDetailRepository.findAll(pageable);
	}

	@Override
	public List<OrderDetail> findAll(Sort sort) {
		return orderDetailRepository.findAll(sort);
	}

	@Override
	public List<OrderDetail> findAllById(Iterable<String> ids) {
		return orderDetailRepository.findAllById(ids);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAll(Iterable<S> entities) {
		return orderDetailRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		orderDetailRepository.flush();
	}

	@Override
	public OrderDetail saveAndFlush(OrderDetail entity) {
		return orderDetailRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities) {
		return orderDetailRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable) {
		return orderDetailRepository.findAll(example, pageable);
	}

	@Override
	public Optional<OrderDetail> findById(String id) {
		return orderDetailRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<OrderDetail> entities) {
		orderDetailRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return orderDetailRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<OrderDetail> entities) {
		orderDetailRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends OrderDetail> boolean exists(Example<S> example) {
		return orderDetailRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		orderDetailRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends OrderDetail, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return orderDetailRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		orderDetailRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		orderDetailRepository.deleteById(id);
	}

	@Override
	public OrderDetail getOne(String id) {
		return orderDetailRepository.getOne(id);
	}

	@Override
	public void delete(OrderDetail entity) {
		orderDetailRepository.delete(entity);
	}

	@Override
	public OrderDetail getById(String id) {
		return orderDetailRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		orderDetailRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends OrderDetail> entities) {
		orderDetailRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		orderDetailRepository.deleteAll();
	}

	@Override
	public OrderDetail getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example) {
		return orderDetailRepository.findAll(example);
	}

	@Override
	public <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort) {
		return orderDetailRepository.findAll(example, sort);
	}

}

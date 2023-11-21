package graduate.service.imp;

import graduate.domain.DriverRegister;
import graduate.domain.Delivery;
import graduate.repository.CartRepository;
import graduate.repository.DeliveryRepository;
import graduate.service.CartService;
import graduate.service.DeliveryService;
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
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public Delivery save(Delivery entity) {
		return deliveryRepository.save(entity);
	}	

	@Override
	public void deleteByOrders_OrderID(String orderID) {
		deliveryRepository.deleteByOrders_OrderID(orderID);
		
	}

	@Override
	public <S extends Delivery> Optional<S> findOne(Example<S> example) {
		return deliveryRepository.findOne(example);
	}

	@Override
	public List<Delivery> findAll() {
		return deliveryRepository.findAll();
	}

	@Override
	public Page<Delivery> findAll(Pageable pageable) {
		return deliveryRepository.findAll(pageable);
	}

	@Override
	public List<Delivery> findAll(Sort sort) {
		return deliveryRepository.findAll(sort);
	}

	@Override
	public List<Delivery> findAllById(Iterable<String> ids) {
		return deliveryRepository.findAllById(ids);
	}

	@Override
	public <S extends Delivery> List<S> saveAll(Iterable<S> entities) {
		return deliveryRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		deliveryRepository.flush();
	}

	@Override
	public Delivery saveAndFlush(Delivery entity) {
		return deliveryRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Delivery> List<S> saveAllAndFlush(Iterable<S> entities) {
		return deliveryRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable) {
		return deliveryRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Delivery> findById(String id) {
		return deliveryRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Delivery> entities) {
		deliveryRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return deliveryRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<Delivery> entities) {
		deliveryRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Delivery> boolean exists(Example<S> example) {
		return deliveryRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		deliveryRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Delivery, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return deliveryRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		deliveryRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		deliveryRepository.deleteById(id);
	}

	@Override
	public Delivery getOne(String id) {
		return deliveryRepository.getOne(id);
	}

	@Override
	public void delete(Delivery entity) {
		deliveryRepository.delete(entity);
	}

	@Override
	public Delivery getById(String id) {
		return deliveryRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		deliveryRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Delivery> entities) {
		deliveryRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		deliveryRepository.deleteAll();
	}

	@Override
	public Delivery getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Delivery> List<S> findAll(Example<S> example) {
		return deliveryRepository.findAll(example);
	}

	@Override
	public <S extends Delivery> List<S> findAll(Example<S> example, Sort sort) {
		return deliveryRepository.findAll(example, sort);
	}


}

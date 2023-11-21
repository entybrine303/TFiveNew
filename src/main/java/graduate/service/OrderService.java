package graduate.service;

import graduate.domain.DriverRegister;
import graduate.domain.Order;
import graduate.domain.Order;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.transaction.annotation.Transactional;

public interface OrderService {
	void deleteByCustomer_CustomerID(String customerID);

	List<Order> findByCustomer_CustomerID(String customerID);
	Order save(Order entity);

	<S extends Order> Optional<S> findOne(Example<S> example);

	List<Order> findAll();

	Page<Order> findAll(Pageable pageable);

	List<Order> findAll(Sort sort);

	List<Order> findAllById(Iterable<String> ids);

	<S extends Order> List<S> saveAll(Iterable<S> entities);

	void flush();

	Order saveAndFlush(Order entity);

	<S extends Order> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Order> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Order> findById(String id);

	void deleteInBatch(Iterable<Order> entities);

	boolean existsById(String id);

	void deleteAllInBatch(List<Order> entities);

	<S extends Order> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Order, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch();

	void deleteById(String id);

	Order getOne(String id);

	void delete(Order entity);

	Order getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends Order> entities);

	void deleteAll();

	Order getReferenceById(String id);

	<S extends Order> List<S> findAll(Example<S> example);

	<S extends Order> List<S> findAll(Example<S> example, Sort sort);
}

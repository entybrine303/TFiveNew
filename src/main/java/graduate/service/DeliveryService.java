package graduate.service;

import graduate.domain.DriverRegister;
import graduate.domain.Delivery;
import graduate.domain.Delivery;
import graduate.domain.Delivery;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface DeliveryService {
	Delivery save(Delivery entity);

	void deleteByOrders_OrderID(String orderID);

	<S extends Delivery> Optional<S> findOne(Example<S> example);

	Delivery findByOrder_OrderId(String orderID);

	Date findLatestUpdateDate(String orderID);

	List<Delivery> findAll();

	Page<Delivery> findAll(Pageable pageable);

	List<Delivery> findAll(Sort sort);

	List<Delivery> findAllById(Iterable<String> ids);

	<S extends Delivery> List<S> saveAll(Iterable<S> entities);

	void flush();

	Delivery saveAndFlush(Delivery entity);

	<S extends Delivery> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Delivery> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Delivery> findById(String id);

	void deleteInBatch(Iterable<Delivery> entities);

	boolean existsById(String id);

	void deleteAllInBatch(List<Delivery> entities);

	<S extends Delivery> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Delivery, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch();

	void deleteById(String id);

	Delivery getOne(String id);

	void delete(Delivery entity);

	Delivery getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends Delivery> entities);

	void deleteAll();

	Delivery getReferenceById(String id);

	<S extends Delivery> List<S> findAll(Example<S> example);

	<S extends Delivery> List<S> findAll(Example<S> example, Sort sort);
}

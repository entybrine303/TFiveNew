package graduate.service;

import graduate.domain.Driver;
import graduate.domain.DriverRegister;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface DriverService {

	Driver findByUsername(String username);
	<S extends Driver> List<S> findAll(Example<S> example, Sort sort);

	<S extends Driver> List<S> findAll(Example<S> example);

	void deleteAll();

	Driver getReferenceById(String id);
	List<Driver> findByNameContaining(String name);
	
	void deleteAll(Iterable<? extends Driver> entities);

	void deleteAllById(Iterable<? extends String> ids);

	Driver getById(String id);

	void delete(Driver entity);

	Driver getOne(String id);

	void deleteById(String id);

	void deleteAllInBatch();

	long count();

	<S extends Driver, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Driver> boolean exists(Example<S> example);

	void deleteAllInBatch(Iterable<Driver> entities);

	<S extends Driver> long count(Example<S> example);

	boolean existsById(String id);

	void deleteInBatch(Iterable<Driver> entities);

	Optional<Driver> findById(String id);

	<S extends Driver> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Driver> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Driver> S saveAndFlush(S entity);

	void flush();

	<S extends Driver> List<S> saveAll(Iterable<S> entities);

	List<Driver> findAllById(Iterable<String> ids);

	List<Driver> findAll(Sort sort);

	Page<Driver> findAll(Pageable pageable);

	List<Driver> findAll();

	<S extends Driver> Optional<S> findOne(Example<S> example);

	<S extends Driver> S save(Driver entity);

}

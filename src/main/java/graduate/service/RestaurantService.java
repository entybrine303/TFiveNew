package graduate.service;

import graduate.domain.Category;
import graduate.domain.Restaurant;
import graduate.domain.DriverRegister;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface RestaurantService {

	Restaurant findByUsername(String username);

	Restaurant save(Restaurant entity);

	<S extends Restaurant> Optional<S> findOne(Example<S> example);

	List<Restaurant> findAll();

	Page<Restaurant> findAll(Pageable pageable);

	List<Restaurant> findAll(Sort sort);

	List<Restaurant> findAllById(Iterable<String> ids);

	<S extends Restaurant> List<S> saveAll(Iterable<S> entities);

	void flush();

	Restaurant saveAndFlush(Restaurant entity);

	<S extends Restaurant> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Restaurant> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Restaurant> findById(String id);

	void deleteInBatch(Iterable<Restaurant> entities);

	boolean existsById(String id);

	void deleteAllInBatch(List<Restaurant> entities);

	<S extends Restaurant> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Restaurant, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch();

	void deleteById(String id);

	Restaurant getOne(String id);

	void delete(Restaurant entity);

	Restaurant getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends Restaurant> entities);

	void deleteAll();

	Restaurant getReferenceById(String id);

	<S extends Restaurant> List<S> findAll(Example<S> example);

	<S extends Restaurant> List<S> findAll(Example<S> example, Sort sort);

}

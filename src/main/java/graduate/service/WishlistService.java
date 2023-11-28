package graduate.service;

import graduate.domain.Wishlist;
import graduate.domain.DriverRegister;
import graduate.domain.Wishlist;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface WishlistService {
	void deleteByCustomer_CustomerID(String customerID);
	
	void deleteByDish_DishID(String customerID);

	List<Wishlist> findByCustomer_CustomerID(String customerID);

	Long countByCustomerID(String userId);

	Wishlist save(Wishlist entity);

	<S extends Wishlist> Optional<S> findOne(Example<S> example);

	List<Wishlist> findAll();

	Page<Wishlist> findAll(Pageable pageable);

	List<Wishlist> findAll(Sort sort);

	List<Wishlist> findAllById(Iterable<String> ids);

	<S extends Wishlist> List<S> saveAll(Iterable<S> entities);

	void flush();

	Wishlist saveAndFlush(Wishlist entity);

	<S extends Wishlist> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Wishlist> Page<S> findAll(Example<S> example, Pageable pageable);

	Optional<Wishlist> findById(String id);

	void deleteInBatch(Iterable<Wishlist> entities);

	boolean existsById(String id);

	void deleteAllInBatch(List<Wishlist> entities);

	<S extends Wishlist> boolean exists(Example<S> example);

	void deleteAllByIdInBatch(Iterable<String> ids);

	<S extends Wishlist, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void deleteAllInBatch();

	void deleteById(String id);

	Wishlist getOne(String id);

	void delete(Wishlist entity);

	Wishlist getById(String id);

	void deleteAllById(Iterable<? extends String> ids);

	void deleteAll(Iterable<? extends Wishlist> entities);

	void deleteAll();

	Wishlist getReferenceById(String id);

	<S extends Wishlist> List<S> findAll(Example<S> example);

	<S extends Wishlist> List<S> findAll(Example<S> example, Sort sort);
}

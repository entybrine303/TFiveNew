package graduate.service;

import graduate.domain.Cart;
import graduate.domain.Cart;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface CartService {
	 void deleteByCustomer_CustomerID(String customerID);
	List<Cart> findByCustomer_CustomerID(String customerID);
  Cart save(Cart entity);

  <S extends Cart> Optional<S> findOne(Example<S> example);

  List<Cart> findAll();

  Page<Cart> findAll(Pageable pageable);

  List<Cart> findAll(Sort sort);

  List<Cart> findAllById(Iterable<String> ids);

  List<Cart> saveAll(List<Cart> entities);

  void flush();

  Cart saveAndFlush(Cart entity);

  <S extends Cart> List<S> saveAllAndFlush(Iterable<S> entities);

  <S extends Cart> Page<S> findAll(Example<S> example, Pageable pageable);

  Optional<Cart> findById(String id);

  void deleteInBatch(Iterable<Cart> entities);

  boolean existsById(String id);

  void deleteAllInBatch(List<Cart> entities);

  <S extends Cart> boolean exists(Example<S> example);

  void deleteAllByIdInBatch(Iterable<String> ids);

  <S extends Cart, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction);

  void deleteAllInBatch();

  void deleteById(String id);

  Cart getOne(String id);

  void delete(Cart entity);

  Cart getById(String id);

  void deleteAllById(Iterable<? extends String> ids);

  void deleteAll(Iterable<? extends Cart> entities);

  void deleteAll();

  Cart getReferenceById(String id);

  <S extends Cart> List<S> findAll(Example<S> example);

  <S extends Cart> List<S> findAll(Example<S> example, Sort sort);
}

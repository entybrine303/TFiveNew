package graduate.service;

import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.DriverRegister;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface CustomerService {

	 Customer findByUsername(String username);

  List<Customer> findByNameContaining(String name);

  Customer save(Customer entity);

  <S extends Customer> Optional<S> findOne(Example<S> example);

  List<Customer> findAll();

  Page<Customer> findAll(Pageable pageable);

  List<Customer> findAll(Sort sort);

  List<Customer> findAllById(Iterable<String> ids);

  <S extends Customer> List<S> saveAll(Iterable<S> entities);

  void flush();

  Customer saveAndFlush(Customer entity);

  <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities);

  <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable);

  Optional<Customer> findById(String id);

  void deleteInBatch(Iterable<Customer> entities);

  boolean existsById(String id);

  void deleteAllInBatch(List<Customer> entities);

  <S extends Customer> boolean exists(Example<S> example);

  void deleteAllByIdInBatch(Iterable<String> ids);

  <S extends Customer, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction);

  void deleteAllInBatch();

  void deleteById(String id);

  Customer getOne(String id);

  void delete(Customer entity);

  Customer getById(String id);

  void deleteAllById(Iterable<? extends String> ids);

  void deleteAll(Iterable<? extends Customer> entities);

  void deleteAll();

  Customer getReferenceById(String id);

  <S extends Customer> List<S> findAll(Example<S> example);

  <S extends Customer> List<S> findAll(Example<S> example, Sort sort);

}

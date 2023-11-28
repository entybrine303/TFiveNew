package graduate.service;

import graduate.domain.Category;
import graduate.domain.Driver;
import graduate.domain.DriverRegister;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface DriverService {

	 Driver findByUsername(String username);

  List<Driver> findByNameContaining(String name);

  Driver save(Driver entity);

  <S extends Driver> Optional<S> findOne(Example<S> example);

  List<Driver> findAll();

  Page<Driver> findAll(Pageable pageable);

  List<Driver> findAll(Sort sort);

  List<Driver> findAllById(Iterable<String> ids);

  <S extends Driver> List<S> saveAll(Iterable<S> entities);

  void flush();

  Driver saveAndFlush(Driver entity);

  <S extends Driver> List<S> saveAllAndFlush(Iterable<S> entities);

  <S extends Driver> Page<S> findAll(Example<S> example, Pageable pageable);

  Optional<Driver> findById(String id);

  void deleteInBatch(Iterable<Driver> entities);

  boolean existsById(String id);

  void deleteAllInBatch(List<Driver> entities);

  <S extends Driver> boolean exists(Example<S> example);

  void deleteAllByIdInBatch(Iterable<String> ids);

  <S extends Driver, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction);

  void deleteAllInBatch();

  void deleteById(String id);

  Driver getOne(String id);

  void delete(Driver entity);

  Driver getById(String id);

  void deleteAllById(Iterable<? extends String> ids);

  void deleteAll(Iterable<? extends Driver> entities);

  void deleteAll();

  Driver getReferenceById(String id);

  <S extends Driver> List<S> findAll(Example<S> example);

  <S extends Driver> List<S> findAll(Example<S> example, Sort sort);

}

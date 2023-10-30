package graduate.service;

import graduate.domain.Voucher;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface VoucherService {

//  List<Voucher> findByNameContaining(String name);
//
//  Page<Voucher> findByNameContaining(String name, Pageable pageable);

  Voucher save(Voucher entity);

  <S extends Voucher> Optional<S> findOne(Example<S> example);

  List<Voucher> findAll();

  Page<Voucher> findAll(Pageable pageable);

  List<Voucher> findAll(Sort sort);

  List<Voucher> findAllById(Iterable<String> ids);

  List<Voucher> saveAll(List<Voucher> entities);

  void flush();

  Voucher saveAndFlush(Voucher entity);

  <S extends Voucher> List<S> saveAllAndFlush(Iterable<S> entities);

  <S extends Voucher> Page<S> findAll(Example<S> example, Pageable pageable);

  Optional<Voucher> findById(String id);

  void deleteInBatch(Iterable<Voucher> entities);

  boolean existsById(String id);

  void deleteAllInBatch(List<Voucher> entities);

  <S extends Voucher> boolean exists(Example<S> example);

  void deleteAllByIdInBatch(Iterable<String> ids);

  <S extends Voucher, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction);

  void deleteAllInBatch();

  void deleteById(String id);

  Voucher getOne(String id);

  void delete(Voucher entity);

  Voucher getById(String id);

  void deleteAllById(Iterable<? extends String> ids);

  void deleteAll(Iterable<? extends Voucher> entities);

  void deleteAll();

  Voucher getReferenceById(String id);

  <S extends Voucher> List<S> findAll(Example<S> example);

  <S extends Voucher> List<S> findAll(Example<S> example, Sort sort);
}

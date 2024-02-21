package graduate.service;

import graduate.domain.DriverRegister;
import graduate.domain.OrderDetail;
import graduate.domain.OrderDetail;
import graduate.domain.OrderDetail;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

public interface OrderDetailService {
	List<OrderDetail> findByOrder_OrderID(String orderID);
	void deleteByOrder_OrderID(String orderID);
  OrderDetail save(OrderDetail entity);

  <S extends OrderDetail> Optional<S> findOne(Example<S> example);

  List<OrderDetail> findAll();

  Page<OrderDetail> findAll(Pageable pageable);

  List<OrderDetail> findAll(Sort sort);

  List<OrderDetail> findAllById(Iterable<String> ids);

  <S extends OrderDetail> List<S> saveAll(Iterable<S> entities);

  void flush();

  OrderDetail saveAndFlush(OrderDetail entity);

  <S extends OrderDetail> List<S> saveAllAndFlush(Iterable<S> entities);

  <S extends OrderDetail> Page<S> findAll(Example<S> example, Pageable pageable);

  Optional<OrderDetail> findById(String id);

  void deleteInBatch(Iterable<OrderDetail> entities);

  boolean existsById(String id);

  void deleteAllInBatch(List<OrderDetail> entities);

  <S extends OrderDetail> boolean exists(Example<S> example);

  void deleteAllByIdInBatch(Iterable<String> ids);

  <S extends OrderDetail, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction);

  void deleteAllInBatch();

  void deleteById(String id);

  OrderDetail getOne(String id);

  void delete(OrderDetail entity);

  OrderDetail getById(String id);

  void deleteAllById(Iterable<? extends String> ids);

  void deleteAll(Iterable<? extends OrderDetail> entities);

  void deleteAll();

  OrderDetail getReferenceById(String id);

  <S extends OrderDetail> List<S> findAll(Example<S> example);

  <S extends OrderDetail> List<S> findAll(Example<S> example, Sort sort);
}

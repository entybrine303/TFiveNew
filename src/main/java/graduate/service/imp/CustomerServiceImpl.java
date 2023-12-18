package graduate.service.imp;


import graduate.domain.Category;
import graduate.domain.Customer;
import graduate.domain.DriverRegister;
import graduate.repository.CustomerRepository;
import graduate.repository.DishRepository;
import graduate.service.CustomerService;
import graduate.service.DishService;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerServiceImpl  implements CustomerService {

  @Autowired
  private CustomerRepository customerReponsitory;

@Override
public Customer findByUsername(String username) {
	return customerReponsitory.findByAccount_Username(username);
}

  @Override
  public List<Customer> findByNameContaining(String name) {
    return customerReponsitory.findByNameContaining(name);
  }

  @Override
  public Customer save(Customer entity) {
    return customerReponsitory.save(entity);
  }

  @Override
  public <S extends Customer> Optional<S> findOne(Example<S> example) {
    return customerReponsitory.findOne(example);
  }

  @Override
  public List<Customer> findAll() {
    return customerReponsitory.findAll();
  }

  @Override
  public Page<Customer> findAll(Pageable pageable) {
    return customerReponsitory.findAll(pageable);
  }

  @Override
  public List<Customer> findAll(Sort sort) {
    return customerReponsitory.findAll(sort);
  }

  @Override
  public List<Customer> findAllById(Iterable<String> ids) {
    return customerReponsitory.findAllById(ids);
  }

  @Override
	public <S extends Customer> List<S> saveAll(Iterable<S> entities) {
		return customerReponsitory.saveAll(entities);
	}

  @Override
  public void flush() {
    customerReponsitory.flush();
  }

  @Override
  public Customer saveAndFlush(Customer entity) {
    return customerReponsitory.saveAndFlush(entity);
  }

  @Override
  public <S extends Customer> List<S> saveAllAndFlush(Iterable<S> entities) {
    return customerReponsitory.saveAllAndFlush(entities);
  }

  @Override
  public <S extends Customer> Page<S> findAll(Example<S> example, Pageable pageable) {
    return customerReponsitory.findAll(example, pageable);
  }

  @Override
  public Optional<Customer> findById(String id) {
    return customerReponsitory.findById(id);
  }

  @Override
  public void deleteInBatch(Iterable<Customer> entities) {
    customerReponsitory.deleteInBatch(entities);
  }

  @Override
  public boolean existsById(String id) {
    return customerReponsitory.existsById(id);
  }

  @Override
  public void deleteAllInBatch(List<Customer> entities) {
    customerReponsitory.deleteAllInBatch(entities);
  }

  @Override
  public <S extends Customer> boolean exists(Example<S> example) {
    return customerReponsitory.exists(example);
  }

  @Override
  public void deleteAllByIdInBatch(Iterable<String> ids) {
    customerReponsitory.deleteAllByIdInBatch(ids);
  }

  @Override
  public <S extends Customer, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction) {
    return customerReponsitory.findBy(example, queryFunction);
  }

  @Override
  public void deleteAllInBatch() {
    customerReponsitory.deleteAllInBatch();
  }

  @Override
  public void deleteById(String id) {
    customerReponsitory.deleteById(id);
  }

  @Override
  public Customer getOne(String id) {
    return customerReponsitory.getOne(id);
  }

  @Override
  public void delete(Customer entity) {
    customerReponsitory.delete(entity);
  }

  @Override
  public Customer getById(String id) {
    return customerReponsitory.getById(id);
  }

  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    customerReponsitory.deleteAllById(ids);
  }

  @Override
  public void deleteAll(Iterable<? extends Customer> entities) {
    customerReponsitory.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    customerReponsitory.deleteAll();
  }

  @Override
  public Customer getReferenceById(String id) {
    return null;
  }

  @Override
  public <S extends Customer> List<S> findAll(Example<S> example) {
    return customerReponsitory.findAll(example);
  }

  @Override
  public <S extends Customer> List<S> findAll(Example<S> example, Sort sort) {
    return customerReponsitory.findAll(example, sort);
  }

@Override
public Customer findByEmail(String email) {
	return customerReponsitory.findByEmail(email);
}



}

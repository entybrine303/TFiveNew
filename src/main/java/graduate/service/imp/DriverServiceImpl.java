package graduate.service.imp;


import graduate.domain.Category;
import graduate.domain.Driver;
import graduate.domain.DriverRegister;
import graduate.repository.CustomerRepository;
import graduate.repository.DishRepository;
import graduate.repository.DriverRepository;
import graduate.service.CustomerService;
import graduate.service.DishService;
import graduate.service.DriverService;

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
public class DriverServiceImpl  implements DriverService {

  @Autowired
  private DriverRepository driverRepository;

@Override
public Driver findByUsername(String username) {
	return driverRepository.findByAccount_Username(username);
}

  @Override
  public List<Driver> findByNameContaining(String name) {
    return driverRepository.findByNameContaining(name);
  }

  @Override
  public Driver save(Driver entity) {
    return driverRepository.save(entity);
  }

  @Override
  public <S extends Driver> Optional<S> findOne(Example<S> example) {
    return driverRepository.findOne(example);
  }

  @Override
  public List<Driver> findAll() {
    return driverRepository.findAll();
  }

  @Override
  public Page<Driver> findAll(Pageable pageable) {
    return driverRepository.findAll(pageable);
  }

  @Override
  public List<Driver> findAll(Sort sort) {
    return driverRepository.findAll(sort);
  }

  @Override
  public List<Driver> findAllById(Iterable<String> ids) {
    return driverRepository.findAllById(ids);
  }

  @Override
	public <S extends Driver> List<S> saveAll(Iterable<S> entities) {
		return driverRepository.saveAll(entities);
	}

  @Override
  public void flush() {
    driverRepository.flush();
  }

  @Override
  public Driver saveAndFlush(Driver entity) {
    return driverRepository.saveAndFlush(entity);
  }

  @Override
  public <S extends Driver> List<S> saveAllAndFlush(Iterable<S> entities) {
    return driverRepository.saveAllAndFlush(entities);
  }

  @Override
  public <S extends Driver> Page<S> findAll(Example<S> example, Pageable pageable) {
    return driverRepository.findAll(example, pageable);
  }

  @Override
  public Optional<Driver> findById(String id) {
    return driverRepository.findById(id);
  }

  @Override
  public void deleteInBatch(Iterable<Driver> entities) {
    driverRepository.deleteInBatch(entities);
  }

  @Override
  public boolean existsById(String id) {
    return driverRepository.existsById(id);
  }

  @Override
  public void deleteAllInBatch(List<Driver> entities) {
    driverRepository.deleteAllInBatch(entities);
  }

  @Override
  public <S extends Driver> boolean exists(Example<S> example) {
    return driverRepository.exists(example);
  }

  @Override
  public void deleteAllByIdInBatch(Iterable<String> ids) {
    driverRepository.deleteAllByIdInBatch(ids);
  }

  @Override
  public <S extends Driver, R> R findBy(Example<S> example,
      Function<FetchableFluentQuery<S>, R> queryFunction) {
    return driverRepository.findBy(example, queryFunction);
  }

  @Override
  public void deleteAllInBatch() {
    driverRepository.deleteAllInBatch();
  }

  @Override
  public void deleteById(String id) {
    driverRepository.deleteById(id);
  }

  @Override
  public Driver getOne(String id) {
    return driverRepository.getOne(id);
  }

  @Override
  public void delete(Driver entity) {
    driverRepository.delete(entity);
  }

  @Override
  public Driver getById(String id) {
    return driverRepository.getById(id);
  }

  @Override
  public void deleteAllById(Iterable<? extends String> ids) {
    driverRepository.deleteAllById(ids);
  }

  @Override
  public void deleteAll(Iterable<? extends Driver> entities) {
    driverRepository.deleteAll(entities);
  }

  @Override
  public void deleteAll() {
    driverRepository.deleteAll();
  }

  @Override
  public Driver getReferenceById(String id) {
    return null;
  }

  @Override
  public <S extends Driver> List<S> findAll(Example<S> example) {
    return driverRepository.findAll(example);
  }

  @Override
  public <S extends Driver> List<S> findAll(Example<S> example, Sort sort) {
    return driverRepository.findAll(example, sort);
  }



}

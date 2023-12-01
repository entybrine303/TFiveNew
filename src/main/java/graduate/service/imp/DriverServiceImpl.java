package graduate.service.imp;

import graduate.domain.Driver;
import graduate.repository.DriverRepository;
import graduate.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Driver findByUsername(String username) {
		return driverRepository.findByAccount_Username(username);
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
	public <S extends Driver> S saveAndFlush(S entity) {
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
	public <S extends Driver> long count(Example<S> example) {
		return driverRepository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Driver> entities) {
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
			Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
		return driverRepository.findBy(example, queryFunction);
	}

	@Override
	public long count() {
		return driverRepository.count();
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
	public Driver getReferenceById(String id) {
		return driverRepository.getReferenceById(id);
	}

	@Override
	public void deleteAll() {
		driverRepository.deleteAll();
	}

	@Override
	public <S extends Driver> List<S> findAll(Example<S> example) {
		return driverRepository.findAll(example);
	}

	@Override
	public <S extends Driver> List<S> findAll(Example<S> example, Sort sort) {
		return driverRepository.findAll(example, sort);
	}

	@Override
	public <S extends Driver> S save(Driver entity) {
		return (S) driverRepository.save(entity);
	}

}

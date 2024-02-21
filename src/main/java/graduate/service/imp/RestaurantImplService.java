package graduate.service.imp;

import graduate.domain.Category;
import graduate.domain.Restaurant;
import graduate.domain.DriverRegister;
import graduate.repository.RestaurantRepository;
import graduate.repository.CustomerRepository;
import graduate.repository.DishRepository;
import graduate.service.RestaurantService;
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
public class RestaurantImplService implements RestaurantService {

	@Autowired
	private RestaurantRepository adminRepository;

	@Override
	public Restaurant findByUsername(String username) {
		return adminRepository.findByAccount_Username(username);
	}

	@Override
	public Restaurant save(Restaurant entity) {
		return adminRepository.save(entity);
	}

	@Override
	public <S extends Restaurant> Optional<S> findOne(Example<S> example) {
		return adminRepository.findOne(example);
	}

	@Override
	public List<Restaurant> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public Page<Restaurant> findAll(Pageable pageable) {
		return adminRepository.findAll(pageable);
	}

	@Override
	public List<Restaurant> findAll(Sort sort) {
		return adminRepository.findAll(sort);
	}

	@Override
	public List<Restaurant> findAllById(Iterable<String> ids) {
		return adminRepository.findAllById(ids);
	}

	@Override
	public <S extends Restaurant> List<S> saveAll(Iterable<S> entities) {
		return adminRepository.saveAll(entities);
	}

	@Override
	public void flush() {
		adminRepository.flush();
	}

	@Override
	public Restaurant saveAndFlush(Restaurant entity) {
		return adminRepository.saveAndFlush(entity);
	}

	@Override
	public <S extends Restaurant> List<S> saveAllAndFlush(Iterable<S> entities) {
		return adminRepository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Restaurant> Page<S> findAll(Example<S> example, Pageable pageable) {
		return adminRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Restaurant> findById(String id) {
		return adminRepository.findById(id);
	}

	@Override
	public void deleteInBatch(Iterable<Restaurant> entities) {
		adminRepository.deleteInBatch(entities);
	}

	@Override
	public boolean existsById(String id) {
		return adminRepository.existsById(id);
	}

	@Override
	public void deleteAllInBatch(List<Restaurant> entities) {
		adminRepository.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Restaurant> boolean exists(Example<S> example) {
		return adminRepository.exists(example);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		adminRepository.deleteAllByIdInBatch(ids);
	}

	@Override
	public <S extends Restaurant, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return adminRepository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllInBatch() {
		adminRepository.deleteAllInBatch();
	}

	@Override
	public void deleteById(String id) {
		adminRepository.deleteById(id);
	}

	@Override
	public Restaurant getOne(String id) {
		return adminRepository.getOne(id);
	}

	@Override
	public void delete(Restaurant entity) {
		adminRepository.delete(entity);
	}

	@Override
	public Restaurant getById(String id) {
		return adminRepository.getById(id);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		adminRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(Iterable<? extends Restaurant> entities) {
		adminRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		adminRepository.deleteAll();
	}

	@Override
	public Restaurant getReferenceById(String id) {
		return null;
	}

	@Override
	public <S extends Restaurant> List<S> findAll(Example<S> example) {
		return adminRepository.findAll(example);
	}

	@Override
	public <S extends Restaurant> List<S> findAll(Example<S> example, Sort sort) {
		return adminRepository.findAll(example, sort);
	}

}
